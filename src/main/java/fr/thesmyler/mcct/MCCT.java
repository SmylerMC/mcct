package fr.thesmyler.mcct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.thesmyler.mcct.inventory.InventoryEvents;
import fr.thesmyler.mcct.tweaks.Tweaks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;

public class MCCT implements ClientModInitializer {
	
	public static final String MOD_ID = "mcct";
	public static final Logger LOGGER = LogManager.getFormatterLogger(MCCT.MOD_ID);
	
	@Override
	public void onInitializeClient() {
		this.registerEvents();
		Tweaks.loadConfig();
		MCCTKeyBindings.registerBindings();
		MinecraftClient.getInstance().getToastManager();
	}
	
	public void registerEvents() {
		ClientTickEvents.END_CLIENT_TICK.register(MCCTKeyBindings::checkBindings);
		ClientTickEvents.END_CLIENT_TICK.register(InventoryEvents::checkForInventoryEvents);
	}
	
}
