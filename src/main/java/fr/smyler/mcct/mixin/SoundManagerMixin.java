package fr.smyler.mcct.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import fr.smyler.mcct.audio.SoundSystemHolder;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.client.sound.SoundSystem;
import net.minecraft.resource.SinglePreparationResourceReloadListener;

@Mixin(SoundManager.class)
public abstract class SoundManagerMixin extends SinglePreparationResourceReloadListener<SoundManager.SoundList> implements SoundSystemHolder{
	
	@Shadow private SoundSystem soundSystem;
	
	@Override
	public SoundSystem getSoundSystem() {
		return this.soundSystem;
	}

}