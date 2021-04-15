package fr.thesmyler.mcct.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.blaze3d.systems.RenderSystem;

import fr.thesmyler.mcct.tweaks.Tweaks;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.tag.FluidTags;

/**
 * Changes the lava fog density
 * 
 * @author SmylerMC
 * 
 */
@Mixin(BackgroundRenderer.class)
public abstract class BackgroundRendererMixin {

	/**
	 * Inject the tweak into applyFog, which is the vanilla fog method
	 * This injection should always be successful, but applyFog is not called anymore if Optifine is installed
	 * 
	 * @param camera
	 * @param fogType
	 * @param viewDistance
	 * @param thickFog
	 * @param info
	 */
	@Inject(at=@At("TAIL"), method="applyFog(Lnet/minecraft/client/render/Camera;Lnet/minecraft/client/render/BackgroundRenderer/FogType;FZ)V")
	private static void fogTweak(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog, CallbackInfo info) {
		mcctLavaFogTweak(camera, fogType, viewDistance, thickFog);
	}
	
	/**
	 * Injects the tweak into setupFog, which is Optifine's fog method
	 * We need this separate injection as the vanilla applyFog method is not used when Optifine is installed
	 * setupFog does not exist in the vanilla game, this injection will therefore fail if Optifine is not installed
	 * 
	 * @param camera
	 * @param fogType
	 * @param viewDistance
	 * @param thickFog
	 * @param afloat
	 * @param info
	 */
	@Inject(at=@At("TAIL"), method="setupFog", require=0) //TODO Make sure the injection is successful if Optifine is installed
	private static void fogTweakOptifine(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog, float afloat, CallbackInfo info) {
		mcctLavaFogTweak(camera, fogType, viewDistance, thickFog);
	}
	
	private static void mcctLavaFogTweak(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog) {
		if(!Tweaks.LAVA.isActivated()) return;
		if (camera.getSubmergedFluidState().matches(FluidTags.LAVA)) RenderSystem.fogDensity(Tweaks.LAVA.FOG_DENSITY.get());
	}

	

}
