package fr.smyler.mcct.inventory;

import fr.smyler.mcct.gui.toasts.GenericToast;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;


//TODO lang files
public abstract class InventoryEvents {
	
	private static boolean lastTickInventoyFull = false;

	public static void checkForInventoryEvents(MinecraftClient client) {
		@SuppressWarnings("resource")
		ClientPlayerEntity player = MinecraftClient.getInstance().player;
		if(player == null) return;
		if(InventoryHelper.isPlayerInventoryFull(player)) {
			if(!lastTickInventoyFull) InventoryEvents.onInventoryBecomesFull();
			lastTickInventoyFull = true;
		} else lastTickInventoyFull = false;
	}
	
	public static void onInventoryBecomesFull() {
		MinecraftClient.getInstance().getToastManager().add(new GenericToast("mcct.toast.invfull.name", "mcct.toast.invfull.desc"));
	}
}
