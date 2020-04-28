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

/**
 * 
 * This mixin only targets the vanilla applyFog method
 * Optifine patches the rendering code in such a way that applyFog is not used anymore,
 * and this mixin is therefore useless when Optifabric and Optifine are present.
 * In such a case, we instead need to inject into Optifine's setupFog method, which is absent in vanilla.
 * This is done in BackgroundRendererOptifineMixin
 * 
 * @author SmylerMC
 * 
 */
@Mixin(BackgroundRenderer.class)
public abstract class BackgroundRendererMixin {

	private static float lavaFogDensity = 0.25F; //TODO Have this somewhere else

	@Inject(at=@At("TAIL"), method="applyFog")
	private static void fogTweak(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog, CallbackInfo info) {
		FluidState fluidState = camera.getSubmergedFluidState();
		if (fluidState.matches(FluidTags.LAVA)) {
			RenderSystem.fogDensity(lavaFogDensity);
		}
	}


}
