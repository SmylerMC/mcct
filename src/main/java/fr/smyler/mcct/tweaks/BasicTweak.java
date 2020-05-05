package fr.smyler.mcct.tweaks;

import java.util.HashMap;
import java.util.Map;

import fr.smyler.mcct.config.ConfigValue;
import fr.smyler.mcct.config.InvalidConfigurationException;



public class BasicTweak extends AbstractTweak {

	public BasicTweak(String id, String name, String longDescription) {
		super(id, name, longDescription);
	}

	@Override
	public Map<String, ConfigValue<?>> getConfiguration() {
		return this.getBaseConfiguration();
	}

	@Override
	public void setFromConfiguration(HashMap<String, Object> configuration) {
		try {
			this.setActivated((boolean)configuration.getOrDefault("activated", this.ACTIVATED.getDefault()));
		} catch(ClassCastException e) {
			throw new InvalidConfigurationException("Invalid config value: " + configuration.getClass().toGenericString());
		}
		
	}

}