package fr.smyler.mcct;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.smyler.mcct.tweaks.Tweaks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;

public class MCCT implements ClientModInitializer {
	
	public static final String MOD_ID = "mcct";
	public static final Logger LOGGER = LogManager.getFormatterLogger(MCCT.MOD_ID);
	
	@Override
	public void onInitializeClient() {
		this.registerEvents();
		Tweaks.loadConfig();
		MCCTKeyBindings.initBindings();
		MCCTKeyBindings.registerBindings();
	}
	
	public void registerEvents() {
		ClientTickCallback.EVENT.register(MCCTKeyBindings::checkBindings);
	}
}
