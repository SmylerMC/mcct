package fr.smyler.mcct.tweaks;

import java.util.HashMap;

import fr.smyler.mcct.config.ConfigValue;
import fr.smyler.mcct.config.InvalidConfigurationException;



public class BasicTweak extends AbstractTweak {

	public BasicTweak(String id, String name, String longDescription) {
		super(id, name, longDescription);
	}

	@Override
	public boolean hasConfiguration() {
		return false;
	}

	@Override
	public HashMap<String, ConfigValue<?>> getConfiguration() {
		HashMap<String, ConfigValue<?>> config = new HashMap<String, ConfigValue<?>>();
		config.put("activated",this.activated);
		return config;
	}

	@Override
	public void setFromConfiguration(HashMap<String, Object> configuration) {
		try {
			this.setActivated((boolean)configuration.get("activated"));
		} catch(ClassCastException e) {
			throw new InvalidConfigurationException("Invalid config value: " + configuration.getClass().toGenericString());
		}
		
	}

}