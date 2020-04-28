package fr.smyler.mcct.tweaks;

import java.util.Collection;
import java.util.HashMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.smyler.mcct.MCCT;

public abstract class Tweaks {
	
	private static HashMap<String, AbstractTweak> tweaks = new HashMap<String, AbstractTweak>();
	
	public static final BasicTweak SWAP_HAND_IN_INVENTORY = new BasicTweak("swap_hand_in_inventory", "swaphandinv_tweak.name", "swaphandinv_tweak.desc");
	public static final BasicTweak SOUND_DEVICE = new BasicTweak("sound_output_device", "sounddevice_tweak.name", "sounddevice_tweak.desc");

	public static void registerTweak(AbstractTweak tweak) {
		MCCT.LOGGER.debug("Registering tweak: " + tweak.getId());
		AbstractTweak other = Tweaks.tweaks.putIfAbsent(tweak.getId(), tweak);
		if(other != null) MCCT.LOGGER.error("Trying to register a tweak which already exists: " + tweak.getId() + " Ignoring it!");
	}
	
	public static Collection<AbstractTweak> getTweaks() {
		return Tweaks.tweaks.values();
	}
	
	public static void setToDefault() {
		Tweaks.SWAP_HAND_IN_INVENTORY.setActivated(true);
		Tweaks.SOUND_DEVICE.setActivated(true);
	}
	
	public static String writeToJson() {
		HashMap<String, HashMap<String, Object>> configTweaks = new HashMap<String, HashMap<String, Object>>();
		for(String key: tweaks.keySet()) {
			configTweaks.put(key, tweaks.get(key).getConfiguration());
		}
		return new GsonBuilder().setPrettyPrinting().create().toJson(new MCCTTweaksConfig(configTweaks));
	}
	
	public static void readFromJson(String jsonString) throws InvalidConfigurationException{
		try {
			HashMap<String, HashMap<String, Object>> config = new Gson().fromJson(jsonString, MCCTTweaksConfig.class).tweaks;
			for(String key: config.keySet()) {
				if(!Tweaks.tweaks.containsKey(key)) throw new InvalidConfigurationException("Invalid tweak id");
				Tweaks.tweaks.get(key).setFromConfiguration(config.get(key));
			}
		} catch(ClassCastException e) {
			throw new InvalidConfigurationException("Invalid Json structure");
		}
	}

}

class MCCTTweaksConfig {
	
	public HashMap<String, HashMap<String, Object>> tweaks;
	
	public MCCTTweaksConfig(HashMap<String, HashMap<String, Object>> tweaks) {
		this.tweaks = tweaks;
	}
	
}
