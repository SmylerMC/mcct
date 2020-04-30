package fr.smyler.mcct.tweaks;

import java.util.Collection;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import fr.smyler.mcct.MCCT;
import fr.smyler.mcct.config.ConfigValue;
import fr.smyler.mcct.config.InvalidConfigurationException;

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
		HashMap<String, HashMap<String, ConfigValue<?>>> configTweaks = new HashMap<String, HashMap<String, ConfigValue<?>>>();
		for(String key: tweaks.keySet()) {
			configTweaks.put(key, tweaks.get(key).getConfiguration());
		}
		return ConfigValue.getSerializerGson().toJson(configTweaks);
	}
	
	public static void readFromJson(String jsonString) {
		try {
			Gson gson = ConfigValue.getSerializerGson();
			HashMap<String, HashMap<String, Object>> config = gson.fromJson(jsonString, new TypeToken<HashMap<String, HashMap<String, Object>>>() {}.getType());
			for(String key: config.keySet()) {
				if(!Tweaks.tweaks.containsKey(key)) throw new InvalidConfigurationException("Invalid tweak id");
				Tweaks.tweaks.get(key).setFromConfiguration(config.get(key));
			}
		} catch(ClassCastException e) {
			throw new InvalidConfigurationException("Invalid Json structure");
		}
	}

}
