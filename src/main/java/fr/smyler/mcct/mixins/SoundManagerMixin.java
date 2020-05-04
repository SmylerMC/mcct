package fr.smyler.mcct.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import fr.smyler.mcct.audio.TweakedSoundManager;
import fr.smyler.mcct.audio.TweakedSoundSystem;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.client.sound.SoundSystem;
import net.minecraft.resource.SinglePreparationResourceReloadListener;

@Mixin(SoundManager.class)
public abstract class SoundManagerMixin extends SinglePreparationResourceReloadListener<SoundManager.SoundList> implements TweakedSoundManager{
	
	@Shadow private SoundSystem soundSystem;
	
	@Override
	public SoundSystem getSoundSystem() {
		return this.soundSystem;
	}
	
	@Override
	public TweakedSoundSystem getTweakedSoundSystem() {
		return (TweakedSoundSystem)getSoundSystem();
	}

}