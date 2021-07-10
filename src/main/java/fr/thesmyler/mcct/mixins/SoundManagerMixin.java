package fr.thesmyler.mcct.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import fr.thesmyler.mcct.audio.TweakedSoundManager;
import fr.thesmyler.mcct.audio.TweakedSoundSystem;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.client.sound.SoundSystem;

@Mixin(SoundManager.class)
//public abstract class SoundManagerMixin extends SinglePreparationResourceReloader<SoundManager.SoundList> implements TweakedSoundManager{
public abstract class SoundManagerMixin implements TweakedSoundManager{
	
	@Shadow private SoundSystem soundSystem;
	
	@Override
	public SoundSystem getSoundSystem() {
		return this.soundSystem;
	}
	
	@Override
	public TweakedSoundSystem getTweakedSoundSystem() {
		return (TweakedSoundSystem) this.getSoundSystem();
	}

}