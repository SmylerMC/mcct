package fr.smyler.mcct.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.fluid.FluidState;
import net.minecraft.tag.FluidTags;

//FIXME Not compatible with Optifine
@Mixin(BackgroundRenderer.class)
public abstract class BackgroundRendererMixin {
	
	public static float lavaFogDensity = 0.25F;

	@Inject(at=@At("TAIL"), method="applyFog")
	private static void fogTweak(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog, CallbackInfo info) {
		FluidState fluidState = camera.getSubmergedFluidState();
		if (fluidState.matches(FluidTags.LAVA)) {
			RenderSystem.fogDensity(lavaFogDensity);
         }
	}
	
}
