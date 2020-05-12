package fr.smyler.mcct;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.smyler.mcct.inventory.InventoryEvents;
import fr.smyler.mcct.tweaks.Tweaks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.minecraft.client.MinecraftClient;

public class MCCT implements ClientModInitializer {
	
	public static final String MOD_ID = "mcct";
	public static final Logger LOGGER = LogManager.getFormatterLogger(MCCT.MOD_ID);
	
	@Override
	public void onInitializeClient() {
		this.registerEvents();
		Tweaks.loadConfig();
		MCCTKeyBindings.initBindings();
		MCCTKeyBindings.registerBindings();
		MinecraftClient.getInstance().getToastManager();
	}
	
	public void registerEvents() {
		ClientTickCallback.EVENT.register(MCCTKeyBindings::checkBindings);
		ClientTickCallback.EVENT.register(InventoryEvents::checkForInventoryEvents);
	}
}
