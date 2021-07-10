package fr.thesmyler.mcct.mixins;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import fr.thesmyler.mcct.MCCT;
import fr.thesmyler.mcct.audio.TweakedSoundEngine;
import fr.thesmyler.mcct.audio.TweakedSoundManager;
import fr.thesmyler.mcct.audio.TweakedSoundSystem;
import fr.thesmyler.mcct.audio.exceptions.ExtensionNotSupportedException;
import fr.thesmyler.mcct.gui.widgets.TweakedButtonWidget;
import fr.thesmyler.mcct.tweaks.Tweaks;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.screen.option.SoundOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ButtonWidget.PressAction;
import net.minecraft.client.option.CyclingOption;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;

@Mixin(SoundOptionsScreen.class)
public abstract class SoundOptionScreenTweakMixin extends GameOptionsScreen {

    private String device = null;
    private List<String> devices;
    private ButtonWidget doneButton = null;

    public SoundOptionScreenTweakMixin(Screen parent, GameOptions gameOptions, Text title) {
        super(parent, gameOptions, title);
    }

    @Inject(at=@At("TAIL"), method="init()V") //FIXME
    public void injectDeviceButton(CallbackInfo info) {
        if(!Tweaks.SOUND_DEVICE.isActivated()) return;
        try {

            TweakedSoundSystem soundSystem = ((TweakedSoundManager)this.client.getSoundManager()).getTweakedSoundSystem();
            TweakedSoundEngine soundEngine = soundSystem.getTweakedSoundEngine();
            String currentDevice = soundEngine.getCurrentDevice();
            this.devices = soundEngine.getAllDevices();
            if(this.devices.size() > 0) {
                CyclingOption<String> deviceOption = CyclingOption.create(
                        MCCT.MOD_ID + ".audio_device",
                        this.devices,
                        device -> Text.of(device),
                        gameOptions -> currentDevice,
                        (gameOptions, option, device) -> this.device = device
                        );
                this.addDrawableChild(deviceOption.createButton(this.gameOptions, this.width/2 + 5, this.height / 6 + 108, 150));
                for(Element child: this.children()) {
                    if(!(child instanceof ButtonWidget)) continue;
                    ButtonWidget button = (ButtonWidget)child;
                    if(button.getMessage().equals(ScreenTexts.DONE)) {
                        this.doneButton = button;
                        break;
                    }
                }
                if(this.doneButton == null) {
                    MCCT.LOGGER.error("Could not find done button in sound option screen");
                } else {
                    PressAction newOnPress = buttonWidget -> {
                        try {
                            if(!soundEngine.getCurrentDevice().equals(this.device)) {
                                Tweaks.SOUND_DEVICE.PREFERRED_DEVICE.set(this.device);
                                soundSystem.restart();
                            }
                        } catch (ExtensionNotSupportedException e) {
                            MCCT.LOGGER.error("Failed to set new audio device because the enumerate all extension is not available, but the extension should have been checked ??");
                        }
                        this.doneButton.onPress();
                    };
                    ButtonWidget newButton = new ButtonWidget(
                            this.doneButton.x, this.doneButton.y,
                            this.doneButton.getWidth(), this.doneButton.getHeight(),
                            this.doneButton.getMessage(),
                            newOnPress,
                            ((TweakedButtonWidget)this.doneButton).getTooltipSupplier());
                    this.remove(this.doneButton);
                    this.addDrawableChild(newButton);
                }
            } else {
                MCCT.LOGGER.error("Failed to find any audio device, falling back to vanilla behavior, whatever it may be");
            }
        } catch (ExtensionNotSupportedException e) {
            MCCT.LOGGER.error("Failed to querry audio device list, extension not available");
        }
    }

}
