package fr.smyler.mcct.tweaks;

import java.util.HashMap;

import fr.smyler.mcct.config.ConfigStringValue;
import fr.smyler.mcct.config.ConfigValue;
import fr.smyler.mcct.config.InvalidConfigurationException;

public class AudioDeviceTweak extends AbstractTweak {
	
	public final ConfigStringValue PREFERRED_DEVICE;

	public AudioDeviceTweak(String id) {
		super(id, "sounddevice_tweak.name", "sounddevice_tweak.desc");
		this.PREFERRED_DEVICE = new ConfigStringValue("", "preferred_device");
	}

	@Override
	public boolean hasConfiguration() {
		return true;
	}

	@Override
	public HashMap<String, ConfigValue<?>> getConfiguration() {
		HashMap<String, ConfigValue<?>> config = new HashMap<String, ConfigValue<?>>();
		config.put("activated", this.ACTIVATED);
		config.put("preferred_device", this.PREFERRED_DEVICE);
		return config;
	}

	@Override
	public void setFromConfiguration(HashMap<String, Object> configuration) throws InvalidConfigurationException {
		this.ACTIVATED.set((boolean)configuration.getOrDefault("activated", this.ACTIVATED.getDefault()));
		this.PREFERRED_DEVICE.set((String)configuration.getOrDefault("preferred_device", this.PREFERRED_DEVICE.getDefault()));
	}

}
