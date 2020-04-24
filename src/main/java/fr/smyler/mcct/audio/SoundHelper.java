package fr.smyler.mcct.audio;

import java.util.List;

import org.lwjgl.openal.AL10;
import org.lwjgl.openal.ALC10;
import org.lwjgl.openal.ALC11;
import org.lwjgl.openal.ALUtil;

import fr.smyler.mcct.MCCT;
import fr.smyler.mcct.audio.exceptions.ExtensionNotSupportedException;
import net.minecraft.client.MinecraftClient;

public abstract class SoundHelper {

	public static boolean isEnumerationExtensionAvailable() {
		return ALC10.alcIsExtensionPresent(0, "ALC_ENUMERATE_ALL_EXT");
	}

	public static TweakedSoundSystem getTweakedSoundSystem() {
		return (TweakedSoundSystem) ((SoundSystemHolder)MinecraftClient.getInstance().getSoundManager()).getSoundSystem();
	}

	public static TweakedSoundEngine getTweakedSoundEngine() {
		return (TweakedSoundEngine) (getTweakedSoundSystem()).getSoundEngine();
	}

	public static List<String> getAllAvailableDevices() throws ExtensionNotSupportedException {
		if(!SoundHelper.isEnumerationExtensionAvailable()) throw new ExtensionNotSupportedException();
		return ALUtil.getStringList(0, ALC11.ALC_ALL_DEVICES_SPECIFIER);
	}

	private static String getALErrorMessage(int errorCode) {
		switch(errorCode) {
		case 40961:
			return "Invalid name parameter.";
		case 40962:
			return "Invalid enumerated parameter value.";
		case 40963:
			return "Invalid parameter parameter value.";
		case 40964:
			return "Invalid operation.";
		case 40965:
			return "Unable to allocate memory.";
		default:
			return "An unrecognized error occurred.";
		}
	}

	public static boolean checkErrors(String sectionName) {
		int errorCode = AL10.alGetError();
		if (errorCode != 0) {
			MCCT.LOGGER.error("{}: {}", sectionName, getALErrorMessage(errorCode));
			return true;
		} else {
			return false;
		}
	}

	private static String getAlcErrorMessage(int errorCode) {
		switch(errorCode) {
		case 40961:
			return "Invalid device.";
		case 40962:
			return "Invalid context.";
		case 40963:
			return "Illegal enum.";
		case 40964:
			return "Invalid value.";
		case 40965:
			return "Unable to allocate memory.";
		default:
			return "An unrecognized error occurred.";
		}
	}

	public static boolean checkAlcErrors(long deviceHandle, String sectionName) {
		int errorCode = ALC10.alcGetError(deviceHandle);
		if (errorCode != 0) {
			MCCT.LOGGER.error("{}{}: {}", sectionName, deviceHandle, getAlcErrorMessage(errorCode));
			return true;
		} else {
			return false;
		}
	}
}
