package fr.smyler.mcct.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import fr.smyler.mcct.MCCT;
import fr.smyler.mcct.audio.TweakedSoundEngine;
import fr.smyler.mcct.audio.TweakedSoundSystem;
import net.minecraft.client.sound.SoundEngine;
import net.minecraft.client.sound.SoundSystem;

@Mixin(SoundSystem.class)
public abstract class SoundSystemMixin implements TweakedSoundSystem{
	
	@Shadow private SoundEngine soundEngine;
	
	@Shadow protected abstract void start();
	@Shadow public abstract void stop();
	
	@Override
	public SoundEngine getSoundEngine() {
		return this.soundEngine;
	}
	
	@Override
	public TweakedSoundEngine getTweakedSoundEngine() {
		return (TweakedSoundEngine)this.soundEngine;
	}
	
	@Override
	public void restart() {
		MCCT.LOGGER.info("Restarting sound system");
		this.stop();
		this.start();
	}

}