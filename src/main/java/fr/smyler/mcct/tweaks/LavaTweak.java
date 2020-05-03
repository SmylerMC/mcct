package fr.smyler.mcct.tweaks;

import java.util.HashMap;

import fr.smyler.mcct.config.ConfigFloatValue;
import fr.smyler.mcct.config.ConfigValue;
import fr.smyler.mcct.config.InvalidConfigurationException;

public class LavaTweak extends AbstractTweak {
	
	public final ConfigFloatValue FOG_DENSITY;

	public LavaTweak(String id) {
		super(id, "lava_tweak.name", "lava_tweak.desc", false);
		this.FOG_DENSITY = new ConfigFloatValue(0.3f, 0f, 3f, "lava_fog_density.comment");
	}

	@Override
	public boolean hasConfiguration() {
		return true;
	}

	@Override
	public HashMap<String, ConfigValue<?>> getConfiguration() {
		HashMap<String, ConfigValue<?>> config = new HashMap<String, ConfigValue<?>>();
		config.put("activated", this.activated);
		config.put("fog_density", this.FOG_DENSITY);
		return config;
	}

	@Override
	public void setFromConfiguration(HashMap<String, Object> configuration) throws InvalidConfigurationException {
		this.activated.set((boolean)configuration.get("activated"));
		this.FOG_DENSITY.set(((Double)configuration.get("fog_density")).floatValue());
	}

}
