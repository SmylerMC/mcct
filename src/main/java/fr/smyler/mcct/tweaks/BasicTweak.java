package fr.smyler.mcct.tweaks;

import java.util.HashMap;

public class BasicTweak extends AbstractTweak {

	public BasicTweak(String id, String name, String longDescription) {
		super(id, name, longDescription);
	}

	@Override
	public boolean hasConfiguration() {
		return false;
	}

	@Override
	public HashMap<String, Object> getConfiguration() {
		HashMap<String, Object> config = new HashMap<String, Object>();
		config.put("activated", this.isActivated());
		return config;
	}

	@Override
	public void setFromConfiguration(HashMap<String, Object> configuration) throws InvalidConfigurationException {
		try {
			this.setActivated((boolean)configuration.get("activated"));
		} catch(ClassCastException e) {
			throw new InvalidConfigurationException("Invalid config value: " + configuration.getClass().toGenericString());
		}
		
	}

}