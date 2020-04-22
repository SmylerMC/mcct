package fr.smyler.mcct.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.ContainerProvider;
import net.minecraft.client.gui.screen.ingame.ContainerScreen;
import net.minecraft.container.Container;
import net.minecraft.container.Slot;
import net.minecraft.container.SlotActionType;
import net.minecraft.text.Text;

//TODO Only works with the survival inventory, implement it for creative and make sure it is not triggered in chests or other containers
@Mixin(ContainerScreen.class)
public abstract class SwitchHandsTweakMixin<T extends Container> extends Screen implements ContainerProvider<T> {

	protected SwitchHandsTweakMixin(Text title) {
		super(title);
	}

	@Shadow protected Slot focusedSlot;

	@Inject(at=@At("HEAD"), method="handleHotbarKeyPressed")
	private void handleHotbarKeyPressed(int keyCode, int scanCode, CallbackInfoReturnable<Boolean> info) {
		if (this.minecraft.player.inventory.getCursorStack().isEmpty() && this.focusedSlot != null) {
			if (minecraft.options.keySwapHands.matchesKey(keyCode, scanCode)) {
				System.out.println("Swaping! Focused:" + focusedSlot.id);
				minecraft.interactionManager.clickSlot(this.getContainer().syncId, 45, 0, SlotActionType.PICKUP, this.minecraft.player);
				minecraft.interactionManager.clickSlot(this.getContainer().syncId, this.focusedSlot.id, 0, SlotActionType.PICKUP, this.minecraft.player);
				minecraft.interactionManager.clickSlot(this.getContainer().syncId, 45, 0, SlotActionType.PICKUP, this.minecraft.player);
			}
		}
	}

}