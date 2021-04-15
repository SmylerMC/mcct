package fr.thesmyler.mcct.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import fr.thesmyler.mcct.tweaks.Tweaks;
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
public abstract class ContainerScreenMixin<T extends Container> extends Screen implements ContainerProvider<T> {

	protected ContainerScreenMixin(Text title) {
		super(title);
	}

	@Shadow protected Slot focusedSlot;

	/**
	 * Let users press F to swap items with their off-hand slot
	 * 
	 * @param keyCode
	 * @param scanCode
	 * @param info
	 */
	@Inject(at=@At("HEAD"), method="handleHotbarKeyPressed(II)Z")
	private void handleHotbarKeyPressed(int keyCode, int scanCode, CallbackInfoReturnable<Boolean> info) {
		if(!Tweaks.INVENTORY.isActivated() || !Tweaks.INVENTORY.SWAP_HAND_KEY_IN_INVENTORY.get()) return;
		if (this.minecraft.player.inventory.getCursorStack().isEmpty() && this.focusedSlot != null && this.focusedSlot.id >= 9 && this.focusedSlot.id != 45) {
			if (minecraft.options.keySwapHands.matchesKey(keyCode, scanCode) && this.minecraft.currentScreen instanceof InventoryScreen) {
				Slot offHandSlot = this.getContainer().getSlot(45);
				int syncId = this.getContainer().syncId;
				if(offHandSlot.getStack().isItemEqualIgnoreDamage(this.focusedSlot.getStack())) {
					Slot startSlot;
					Slot destSlot;
					if(offHandSlot.getStack().getCount() > this.focusedSlot.getStack().getCount()) {
						startSlot = offHandSlot;
						destSlot = this.focusedSlot;
					} else {
						startSlot = this.focusedSlot;
						destSlot = offHandSlot;
					}
					int diff = startSlot.getStack().getCount() - destSlot.getStack().getCount();
					this.minecraft.interactionManager.clickSlot(syncId, startSlot.id, 0, SlotActionType.PICKUP, this.minecraft.player);
					while(diff-- > 0) minecraft.interactionManager.clickSlot(syncId, destSlot.id, 1, SlotActionType.PICKUP, this.minecraft.player);
					this.minecraft.interactionManager.clickSlot(syncId, startSlot.id, 0, SlotActionType.PICKUP, this.minecraft.player);
				} else {
					this.minecraft.interactionManager.clickSlot(syncId, 45, 0, SlotActionType.PICKUP, this.minecraft.player);
					this.minecraft.interactionManager.clickSlot(syncId, this.focusedSlot.id, 0, SlotActionType.PICKUP, this.minecraft.player);
					this.minecraft.interactionManager.clickSlot(syncId, 45, 0, SlotActionType.PICKUP, this.minecraft.player);
				}
			}
		}
	}

}