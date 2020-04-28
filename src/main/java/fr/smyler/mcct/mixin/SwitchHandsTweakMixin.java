package fr.smyler.mcct.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import fr.smyler.mcct.tweaks.Tweaks;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.ContainerProvider;
import net.minecraft.client.gui.screen.ingame.ContainerScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.container.Container;
import net.minecraft.container.Slot;
import net.minecraft.container.SlotActionType;
import net.minecraft.text.Text;

//TODO Only works with the survival inventory, implement it for creative
@Mixin(ContainerScreen.class)
public abstract class SwitchHandsTweakMixin<T extends Container> extends Screen implements ContainerProvider<T> {

	protected SwitchHandsTweakMixin(Text title) {
		super(title);
	}

	@Shadow protected Slot focusedSlot;

	@Inject(at=@At("HEAD"), method="handleHotbarKeyPressed")
	private void handleHotbarKeyPressed(int keyCode, int scanCode, CallbackInfoReturnable<Boolean> info) {
		if(!Tweaks.SWAP_HAND_IN_INVENTORY.isActivated()) return;
		if (this.minecraft.player.inventory.getCursorStack().isEmpty() && this.focusedSlot != null && this.focusedSlot.id >= 9) {
			if (minecraft.options.keySwapHands.matchesKey(keyCode, scanCode) && this.minecraft.currentScreen instanceof InventoryScreen) {
				minecraft.interactionManager.clickSlot(this.getContainer().syncId, 45, 0, SlotActionType.PICKUP, this.minecraft.player);
				minecraft.interactionManager.clickSlot(this.getContainer().syncId, this.focusedSlot.id, 0, SlotActionType.PICKUP, this.minecraft.player);
				minecraft.interactionManager.clickSlot(this.getContainer().syncId, 45, 0, SlotActionType.PICKUP, this.minecraft.player);
			}
		}
	}

}