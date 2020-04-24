package fr.smyler.mcct.audio;

import net.minecraft.client.sound.SoundEngine;

public interface TweakedSoundSystem {
	
	public SoundEngine getSoundEngine();
	public TweakedSoundEngine getTweakedSoundEngine();
	
	public void restart();
	
}
