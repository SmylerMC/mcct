package fr.thesmyler.mcct.inventory;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;

public abstract class InventoryHelper {

	/**
	 * Returns true if the player has at least 1 item in each slot of his inventory, excluding off-hand and armor
	 * 
	 * @param player
	 * @return
	 */
	public static boolean isPlayerInventoryFull(PlayerEntity player) {
		Inventory inventory = player.inventory;
		for(int i=0;i<inventory.getInvSize() - 5; i++) { // We assume the last five are armor and offhand
			if(inventory.getInvStack(i).isEmpty()) return false;
		}
		return true;
	}
}
