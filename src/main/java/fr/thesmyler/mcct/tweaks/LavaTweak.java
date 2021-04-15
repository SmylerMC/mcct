package fr.thesmyler.mcct.tweaks;

import java.util.Map;

import fr.thesmyler.mcct.config.ConfigFloatValue;
import fr.thesmyler.mcct.config.ConfigValue;
import fr.thesmyler.mcct.config.InvalidConfigurationException;

public class LavaTweak extends AbstractTweak {
	
	public final ConfigFloatValue FOG_DENSITY;

	public LavaTweak(String id) {
		super(id, "lava_tweak.name", "lava_tweak.desc", false);
		this.FOG_DENSITY = new ConfigFloatValue(0.3f, 0f, 3f, "lava_fog_density.comment");
	}

	@Override
	public Map<String, ConfigValue<?>> getConfiguration() {
		Map<String, ConfigValue<?>> config = this.getBaseConfiguration();
		config.put("fog_density", this.FOG_DENSITY);
		return config;
	}

	@Override
	public void setFromConfiguration(Map<String, Object> configuration) throws InvalidConfigurationException {
		this.ACTIVATED.set((boolean)configuration.getOrDefault("activated", this.ACTIVATED.getDefault()));
		this.FOG_DENSITY.set(((Double)configuration.getOrDefault("fog_density", this.FOG_DENSITY.getDefault())).floatValue());
	}

}
