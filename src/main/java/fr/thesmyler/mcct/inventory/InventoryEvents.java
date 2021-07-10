package fr.thesmyler.mcct.inventory;

import java.util.HashMap;
import java.util.Map;

import fr.thesmyler.mcct.gui.toasts.GenericToast;
import fr.thesmyler.mcct.tweaks.Tweaks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;


public abstract class InventoryEvents {
	
	private static boolean lastTickInventoyFull = false;
	private static Map<Integer, ItemStack> trackedDamagableStacks = new HashMap<>();

	public static void checkForInventoryEvents(MinecraftClient client) {
		if(!Tweaks.INVENTORY.isActivated()) return;
		
		@SuppressWarnings("resource")
		ClientPlayerEntity player = MinecraftClient.getInstance().player;
		if(player == null) return;
		if(Tweaks.INVENTORY.ALERT_ON_FULL_INVENTORY.get() && InventoryHelper.isPlayerInventoryFull(player)) {
			if(!lastTickInventoyFull) InventoryEvents.onInventoryBecomesFull();
			lastTickInventoyFull = true;
		} else lastTickInventoyFull = false;
		
		Inventory inventory = client.player.getInventory();
		for(int slot: trackedDamagableStacks.keySet()) {
			ItemStack newStack = inventory.getStack(slot);
			ItemStack oldStack = trackedDamagableStacks.get(slot);
			if(newStack.isItemEqualIgnoreDamage(oldStack) && newStack.getDamage() != oldStack.getDamage()) {
				onItemDamageChanged(newStack, oldStack.getDamage());
			}
		}
		trackedDamagableStacks.clear();
		for(int slot = 0; slot < inventory.size(); slot++) {
			ItemStack stack = inventory.getStack(slot);
			if(stack.isDamageable()) trackedDamagableStacks.put(slot, stack);
		}
	}
	
	public static void onInventoryBecomesFull() {
		MinecraftClient.getInstance().getToastManager().add(new GenericToast("mcct.toast.invfull.name", null, new ItemStack(Items.CHEST)));
	}
	
	public static void onItemDamageChanged(ItemStack stack, int previousDamage) {
		float currentDam = (float)stack.getDamage() / stack.getMaxDamage() * 100f;
		float previousDam = (float)previousDamage / stack.getMaxDamage() * 100f;
		float threshold = 100f - Tweaks.INVENTORY.LOW_DURABILITY_THRESHOLD.get();
		if(Tweaks.INVENTORY.ALERT_ON_LOW_DURABILITY.get() && currentDam >= threshold && previousDam < threshold) {
			MinecraftClient.getInstance().getToastManager().add(new GenericToast("mcct.toast.tooldamage.name", null, stack));
		}
	}
}
