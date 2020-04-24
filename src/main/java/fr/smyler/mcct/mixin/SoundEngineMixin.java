package fr.smyler.mcct.mixin;

import java.util.List;

import org.lwjgl.openal.ALC10;
import org.lwjgl.openal.ALC11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import fr.smyler.mcct.MCCT;
import fr.smyler.mcct.audio.SoundHelper;
import fr.smyler.mcct.audio.TweakedSoundEngine;
import fr.smyler.mcct.audio.exceptions.ExtensionNotSupportedException;
import net.minecraft.client.sound.SoundEngine;

@Mixin(SoundEngine.class)
public abstract class SoundEngineMixin implements TweakedSoundEngine{

	@Shadow private long devicePointer;

	@Shadow public abstract void close();
	@Shadow public abstract void init();
	
	private static String preferredDevice = null;

	@Override
	public String getCurrentDevice() throws ExtensionNotSupportedException {
		if(!SoundHelper.isEnumerationExtensionAvailable()) throw new ExtensionNotSupportedException();
		return ALC10.alcGetString(this.devicePointer, ALC11.ALC_ALL_DEVICES_SPECIFIER);
	}
	
	/**
	 * Just a shortcut to {@link SoundHelper#getAllAvailableDevices()}
	 */
	@Override
	public List<String> getAllDevices() throws ExtensionNotSupportedException {
		return SoundHelper.getAllAvailableDevices();
	}

	@Override
	public void setPreferredDevice(String deviceName) {
		SoundEngineMixin.preferredDevice = deviceName;
		MCCT.LOGGER.info("Changed preferred audio device to " + deviceName);
	}

	private static long openDevice(String deviceName) {
		for(int i = 0; i < 3; ++i) {
			long l = ALC10.alcOpenDevice(deviceName);
			if (l != 0L && !SoundHelper.checkAlcErrors(l, "Open device")) {
				return l;
			}
		}

		throw new IllegalStateException("Failed to open OpenAL device");

	}
	
	@Inject(at=@At("HEAD"), method="openDevice()L", cancellable=true)
	private static void proxyOpenDevice(CallbackInfoReturnable<Long> info) {
		if(preferredDevice != null) {
			try {
				if(SoundHelper.getAllAvailableDevices().contains(preferredDevice)) {
					MCCT.LOGGER.info("Openning preferred audio device " + preferredDevice);
					info.setReturnValue(openDevice(preferredDevice));
				} else {
					MCCT.LOGGER.error("Preferred audio device does not exist! Falling back to default device");
				}
			} catch (ExtensionNotSupportedException e) {
				MCCT.LOGGER.error("Falling back to openning default audio device as the enumeration extension is not present");
			}
		} else {
			MCCT.LOGGER.info("Openning default audio device");
		}
	}
}
