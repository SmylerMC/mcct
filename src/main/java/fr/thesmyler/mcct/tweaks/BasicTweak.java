package fr.thesmyler.mcct.tweaks;

import java.util.Map;

import fr.thesmyler.mcct.config.ConfigValue;
import fr.thesmyler.mcct.config.InvalidConfigurationException;



public class BasicTweak extends AbstractTweak {

	public BasicTweak(String id, String name, String longDescription) {
		super(id, name, longDescription);
	}

	@Override
	public Map<String, ConfigValue<?>> getConfiguration() {
		return this.getBaseConfiguration();
	}

	@Override
	public void setFromConfiguration(Map<String, Object> configuration) {
		try {
			this.setActivated((boolean)configuration.getOrDefault("activated", this.ACTIVATED.getDefault()));
		} catch(ClassCastException e) {
			throw new InvalidConfigurationException("Invalid config value: " + configuration.getClass().toGenericString());
		}
		
	}

}