package fr.smyler.mcct;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.smyler.mcct.tweaks.InvalidConfigurationException;
import fr.smyler.mcct.tweaks.Tweaks;
import net.fabricmc.api.ClientModInitializer;

public class MCCT implements ClientModInitializer {
	
	public static final String MOD_ID = "mcct";
	public static final Logger LOGGER = LogManager.getLogger("MOD_ID");
	
	@Override
	public void onInitializeClient() {
		this.registerEvents();
		LOGGER.info(Tweaks.writeToJson());
		Tweaks.SOUND_DEVICE.setActivated(true);
		String conf = Tweaks.writeToJson();
		LOGGER.info(conf);
		Tweaks.setToDefault();
		LOGGER.info(Tweaks.writeToJson());
		try {
			Tweaks.readFromJson(conf);
		} catch (InvalidConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LOGGER.info(Tweaks.writeToJson());
		
	}
	
	public void registerEvents() {
		
	}
}
