package fr.thesmyler.mcct.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import fr.thesmyler.mcct.MCCT;
import fr.thesmyler.mcct.tweaks.Tweaks;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.toast.AdvancementToast;
import net.minecraft.client.toast.RecipeToast;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.client.toast.Toast;
import net.minecraft.client.toast.ToastManager;
import net.minecraft.client.toast.TutorialToast;

@Mixin(value=ToastManager.class)
public abstract class ToastManagerMixin extends DrawableHelper{

	@Inject(at=@At("HEAD"), method="add(Lnet/minecraft/client/toast/Toast;)V", cancellable=true)
	public void addToastTweak(Toast toast, CallbackInfo info) {
		if(!Tweaks.TOASTS.isActivated()) return;
		if(Tweaks.TOASTS.HIDE_ALL.get()) info.cancel();
		else if(!Tweaks.TOASTS.SHOW_ADVANCEMENT.get() && toast instanceof AdvancementToast) info.cancel();
		else if(!Tweaks.TOASTS.SHOW_SYSTEM.get() && toast instanceof SystemToast) info.cancel();
		else if(!Tweaks.TOASTS.SHOW_RECIPE.get() && toast instanceof RecipeToast) info.cancel();
		else if(!Tweaks.TOASTS.SHOW_TUTORIAL.get() && toast instanceof TutorialToast) info.cancel();
		if(info.isCancelled()) {
			MCCT.LOGGER.debug("Cancelling toast: " + toast.getClass());
		}
		
	}
}
