package fr.thesmyler.mcct.audio;

import java.util.List;

import fr.thesmyler.mcct.audio.exceptions.ExtensionNotSupportedException;

public interface TweakedSoundEngine {
	
	public String getCurrentDevice() throws ExtensionNotSupportedException;
	
	public List<String> getAllDevices() throws ExtensionNotSupportedException;

}
