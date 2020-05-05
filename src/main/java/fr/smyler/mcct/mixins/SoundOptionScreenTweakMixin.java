package fr.smyler.mcct.mixins;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import fr.smyler.mcct.MCCT;
import fr.smyler.mcct.audio.TweakedSoundEngine;
import fr.smyler.mcct.audio.TweakedSoundManager;
import fr.smyler.mcct.audio.TweakedSoundSystem;
import fr.smyler.mcct.audio.exceptions.ExtensionNotSupportedException;
import fr.smyler.mcct.tweaks.Tweaks;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.options.GameOptionsScreen;
import net.minecraft.client.gui.screen.options.SoundOptionsScreen;
import net.minecraft.client.gui.widget.AbstractButtonWidget;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.options.CyclingOption;
import net.minecraft.client.options.GameOptions;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.Text;

@Mixin(SoundOptionsScreen.class)
public abstract class SoundOptionScreenTweakMixin extends GameOptionsScreen {

	private int deviceId = 0;
	private List<String> devices;
	private ButtonWidget doneButton = null;

	public SoundOptionScreenTweakMixin(Screen parent, GameOptions gameOptions, Text title) {
		super(parent, gameOptions, title);
	}

	@Inject(at=@At("TAIL"), method="init")
	public void injectDeviceButton(CallbackInfo info) {
		if(!Tweaks.SOUND_DEVICE.isActivated()) return;
		try {
			
			TweakedSoundSystem soundSystem = ((TweakedSoundManager)this.minecraft.getSoundManager()).getTweakedSoundSystem();
			TweakedSoundEngine soundEngine = soundSystem.getTweakedSoundEngine();
			this.devices = soundEngine.getAllDevices();
			if(devices.size() > 0) {
				this.deviceId = devices.indexOf(soundEngine.getCurrentDevice());
				if(this.deviceId == -1) this.deviceId = 0;
						CyclingOption deviceOption = new CyclingOption(MCCT.MOD_ID + ".audio_device",
								(gameOptions, integer) -> {
									deviceId = (deviceId + integer) % devices.size();
								},
								(gameOptions, cyclingOptions) -> devices.get(deviceId)
								);
				this.addButton(deviceOption.createButton(gameOptions, this.width/2 + 5, this.height / 6 + 108, 150));
				for(AbstractButtonWidget abstractButton: this.buttons) {
					if(!(abstractButton instanceof ButtonWidget)) continue;
					ButtonWidget button = (ButtonWidget)abstractButton;
					if(button.getMessage().equals(I18n.translate("gui.done"))) {
						doneButton = button;
						break;
					}
				}
				if(doneButton == null) {
					MCCT.LOGGER.error("Could not find done button in sound option screen");
				} else {
					int buttonIndex = this.buttons.indexOf(doneButton);
					ButtonWidget newButton = new ButtonWidget(
							doneButton.x, doneButton.y, doneButton.getWidth(), 20, doneButton.getMessage(), (buttonWidget) -> {
								String newDevice = devices.get(deviceId);
								try {
									if(!soundEngine.getCurrentDevice().equals(newDevice)) {
										Tweaks.SOUND_DEVICE.PREFERRED_DEVICE.set(devices.get(deviceId));
										soundSystem.restart();
									}
								} catch (ExtensionNotSupportedException e) {
									MCCT.LOGGER.error("Failed to set new device because the enumerate all extension is not available, but the extension sould have been checked ??");
								}
								doneButton.onPress();
							}
					);
					this.buttons.set(buttonIndex, newButton);
					int childIndex = this.children.indexOf(doneButton);
					this.children.set(childIndex, newButton);
				}
			} else {
				MCCT.LOGGER.error("Failed to find any audio device, falling back to vanilla behavior, whatever it may be");
			}
		} catch (ExtensionNotSupportedException e) {
			MCCT.LOGGER.error("Failed to querry audio device list, extension not available");
		}
	}

}
