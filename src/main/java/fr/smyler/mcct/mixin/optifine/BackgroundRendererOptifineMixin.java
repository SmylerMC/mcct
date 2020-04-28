package fr.smyler.mcct.mixin.optifine;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.fluid.FluidState;
import net.minecraft.tag.FluidTags;

/**
 * Optifine changes the fog rendering and does not use the vanilla applyFog method.
 * We therefore needs to inject into the setupFog method, which is injected by Optifine
 * 
 * @author SmylerMC
 *
 */
//FIXME Do not apply this mixin if Optifine is not present to avoid logging the exception
@Mixin(BackgroundRenderer.class)
public abstract class BackgroundRendererOptifineMixin {

	private static float lavaFogDensityOptifine = 0.25F; //TODO Have this somewhere else
	
	@Inject(at=@At("TAIL"), method="setupFog")
	private static void fogTweakOptifine(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog, float afloat, CallbackInfo info) {
		FluidState fluidState = camera.getSubmergedFluidState();
		if (fluidState.matches(FluidTags.LAVA)) {
			RenderSystem.fogDensity(lavaFogDensityOptifine);
		}
	}

}
