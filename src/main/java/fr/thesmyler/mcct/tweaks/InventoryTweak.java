package fr.thesmyler.mcct.tweaks;

import java.util.Map;

import fr.thesmyler.mcct.config.ConfigBooleanValue;
import fr.thesmyler.mcct.config.ConfigFloatValue;
import fr.thesmyler.mcct.config.ConfigValue;
import fr.thesmyler.mcct.config.InvalidConfigurationException;

public class InventoryTweak extends AbstractTweak {
	
	public final ConfigBooleanValue MOUSE_WHEEL_IN_RECIPE_BOOK;
	public final ConfigBooleanValue INVERTED_MOUSE_WHEEL;
	public final ConfigBooleanValue ALERT_ON_FULL_INVENTORY;
	public final ConfigBooleanValue ALERT_ON_LOW_DURABILITY;
	public final ConfigFloatValue LOW_DURABILITY_THRESHOLD;

	public InventoryTweak(String id) {
		super(id, "inventory_tweak.name", "inventory_tweak.desc");
		this.MOUSE_WHEEL_IN_RECIPE_BOOK = new ConfigBooleanValue(true, "wheel_in_recipe_book");
		this.INVERTED_MOUSE_WHEEL = new ConfigBooleanValue(false, "inverted_mouse_wheel");
		this.ALERT_ON_FULL_INVENTORY = new ConfigBooleanValue(false, "alert_on_full_inventory");
		this.ALERT_ON_LOW_DURABILITY = new ConfigBooleanValue(false, "alert_on_low_durability");
		this.LOW_DURABILITY_THRESHOLD = new ConfigFloatValue(5f, 0f, 100f, "low_durability_threshold");
	}

	@Override
	public Map<String, ConfigValue<?>> getConfiguration() {
		Map<String, ConfigValue<?>> config = this.getBaseConfiguration();
		config.put("activate_wheel_in_recipe_book", this.MOUSE_WHEEL_IN_RECIPE_BOOK);
		config.put("inverted_mouse_wheel", this.INVERTED_MOUSE_WHEEL);
		config.put("alert_on_full_inventory", this.ALERT_ON_FULL_INVENTORY);
		config.put("alert_on_low_durability", this.ALERT_ON_LOW_DURABILITY);
		config.put("low_durability_threshold", this.LOW_DURABILITY_THRESHOLD);
		return config;
	}

	@Override
	public void setFromConfiguration(Map<String, Object> configuration) throws InvalidConfigurationException {
		this.MOUSE_WHEEL_IN_RECIPE_BOOK.set((Boolean)configuration.getOrDefault("activate_wheel_in_recipe_book", this.MOUSE_WHEEL_IN_RECIPE_BOOK.getDefault()));
		this.INVERTED_MOUSE_WHEEL.set((Boolean)configuration.getOrDefault("inverted_mouse_wheel", this.INVERTED_MOUSE_WHEEL.getDefault()));
		this.ALERT_ON_FULL_INVENTORY.set((Boolean)configuration.getOrDefault("alert_on_full_inventory", this.ALERT_ON_FULL_INVENTORY.getDefault()));
		this.ALERT_ON_LOW_DURABILITY.set((Boolean)configuration.getOrDefault("alert_on_low_durability", this.ALERT_ON_LOW_DURABILITY.getDefault()));
		this.LOW_DURABILITY_THRESHOLD.set((float) configuration.getOrDefault("low_durability_threshold", this.LOW_DURABILITY_THRESHOLD.getDefault()));
	}

}
