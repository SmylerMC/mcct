package fr.smyler.mcct.inventory;

import fr.smyler.mcct.gui.toasts.GenericToast;
import fr.smyler.mcct.tweaks.Tweaks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;


//TODO lang files
public abstract class InventoryEvents {
	
	private static boolean lastTickInventoyFull = false;

	public static void checkForInventoryEvents(MinecraftClient client) {
		if(!Tweaks.INVENTORY.isActivated()) return;
		@SuppressWarnings("resource")
		ClientPlayerEntity player = MinecraftClient.getInstance().player;
		if(player == null) return;
		if(Tweaks.INVENTORY.ALERT_ON_FULL_INVENTORY.get() && InventoryHelper.isPlayerInventoryFull(player)) {
			if(!lastTickInventoyFull) InventoryEvents.onInventoryBecomesFull();
			lastTickInventoyFull = true;
		} else lastTickInventoyFull = false;
	}
	
	public static void onInventoryBecomesFull() {
		MinecraftClient.getInstance().getToastManager().add(new GenericToast("mcct.toast.invfull.name", null, new ItemStack(Items.CHEST)));
	}
}
