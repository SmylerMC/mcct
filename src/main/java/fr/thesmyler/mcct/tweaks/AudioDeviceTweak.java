package fr.thesmyler.mcct.tweaks;

import java.util.Map;

import fr.thesmyler.mcct.config.ConfigStringValue;
import fr.thesmyler.mcct.config.ConfigValue;
import fr.thesmyler.mcct.config.InvalidConfigurationException;

public class AudioDeviceTweak extends AbstractTweak {
	
	public final ConfigStringValue PREFERRED_DEVICE;

	public AudioDeviceTweak(String id) {
		super(id, "sounddevice_tweak.name", "sounddevice_tweak.desc");
		this.PREFERRED_DEVICE = new ConfigStringValue("", "preferred_device");
	}

	@Override
	public Map<String, ConfigValue<?>> getConfiguration() {
		Map<String, ConfigValue<?>> config = this.getBaseConfiguration();
		config.put("preferred_device", this.PREFERRED_DEVICE);
		return config;
	}

	@Override
	public void setFromConfiguration(Map<String, Object> configuration) throws InvalidConfigurationException {
		this.ACTIVATED.set((boolean)configuration.getOrDefault("activated", this.ACTIVATED.getDefault()));
		this.PREFERRED_DEVICE.set((String)configuration.getOrDefault("preferred_device", this.PREFERRED_DEVICE.getDefault()));
	}

}
