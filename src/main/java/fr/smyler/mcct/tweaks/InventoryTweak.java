package fr.smyler.mcct.tweaks;

import java.util.Map;

import fr.smyler.mcct.config.ConfigBooleanValue;
import fr.smyler.mcct.config.ConfigValue;
import fr.smyler.mcct.config.InvalidConfigurationException;

public class InventoryTweak extends AbstractTweak {
	
	public final ConfigBooleanValue SWAP_HAND_KEY_IN_INVENTORY;
	public final ConfigBooleanValue MOUSE_WHEEL_IN_RECIPE_BOOK;
	public final ConfigBooleanValue INVERTED_MOUSE_WHEEL;

	public InventoryTweak(String id) {
		super(id, "inventory_tweak.name", "inventory_tweak.desc");
		this.SWAP_HAND_KEY_IN_INVENTORY = new ConfigBooleanValue(true, "swap_hand_key");
		this.MOUSE_WHEEL_IN_RECIPE_BOOK = new ConfigBooleanValue(true, "wheel_in_recipe_book");
		this.INVERTED_MOUSE_WHEEL = new ConfigBooleanValue(false, "inverted_mouse_wheel");
	}

	@Override
	public Map<String, ConfigValue<?>> getConfiguration() {
		Map<String, ConfigValue<?>> config = this.getBaseConfiguration();
		config.put("activate_swap_hand_key", this.SWAP_HAND_KEY_IN_INVENTORY);
		config.put("activate_wheel_in_recipe_book", this.MOUSE_WHEEL_IN_RECIPE_BOOK);
		config.put("inverted_mouse_wheel", this.INVERTED_MOUSE_WHEEL);
		return config;
	}

	@Override
	public void setFromConfiguration(Map<String, Object> configuration) throws InvalidConfigurationException {
		this.SWAP_HAND_KEY_IN_INVENTORY.set((Boolean)configuration.getOrDefault("swap_hand_key", this.SWAP_HAND_KEY_IN_INVENTORY.getDefault()));
		this.MOUSE_WHEEL_IN_RECIPE_BOOK.set((Boolean)configuration.getOrDefault("activate_wheel_in_recipe_book", this.MOUSE_WHEEL_IN_RECIPE_BOOK.getDefault()));
		this.INVERTED_MOUSE_WHEEL.set((Boolean)configuration.getOrDefault("inverted_mouse_wheel", this.INVERTED_MOUSE_WHEEL.getDefault()));
	}

}
