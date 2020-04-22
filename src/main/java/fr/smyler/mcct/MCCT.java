package fr.smyler.mcct;

import net.fabricmc.api.ClientModInitializer;

public class MCCT implements ClientModInitializer {
	
	@Override
	public void onInitializeClient() {
		this.registerEvents();
	}
	
	public void registerEvents() {
	}
}
