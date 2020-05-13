package fr.smyler.mcct.tweaks;

import java.util.Map;

import fr.smyler.mcct.config.ConfigBooleanValue;
import fr.smyler.mcct.config.ConfigValue;
import fr.smyler.mcct.config.InvalidConfigurationException;

public class ToastTweak extends AbstractTweak {
	
	public final ConfigBooleanValue HIDE_ALL; //This is really all toasts, including our own!
	public final ConfigBooleanValue SHOW_SYSTEM;
	public final ConfigBooleanValue SHOW_RECIPE;
	public final ConfigBooleanValue SHOW_ADVANCEMENT;
	public final ConfigBooleanValue SHOW_TUTORIAL;

	public ToastTweak(String id) {
		super(id, "toast_tweak.name", "toast_tweak.desc");
		this.HIDE_ALL = new ConfigBooleanValue(false, "hide_all_toasts");
		this.SHOW_SYSTEM = new ConfigBooleanValue(true, "show_system_toasts");
		this.SHOW_RECIPE = new ConfigBooleanValue(true, "show_recipe_toasts");
		this.SHOW_ADVANCEMENT = new ConfigBooleanValue(true, "show_advancement_toasts");
		this.SHOW_TUTORIAL = new ConfigBooleanValue(true, "show_tutorial_toasts");
	}

	@Override
	public Map<String, ConfigValue<?>> getConfiguration() {
		Map<String, ConfigValue<?>> config = this.getBaseConfiguration();
		config.put("hide_all_toasts", this.HIDE_ALL);
		config.put("show_system_toasts", this.SHOW_SYSTEM);
		config.put("show_recipe_toasts", this.SHOW_RECIPE);
		config.put("show_advancement_toasts", this.SHOW_ADVANCEMENT);
		config.put("show_tutorial_toasts", this.SHOW_TUTORIAL);
		return config;
	}

	@Override
	public void setFromConfiguration(Map<String, Object> configuration) throws InvalidConfigurationException {
		// TODO Auto-generated method stub

	}

}
