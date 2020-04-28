package fr.smyler.mcct.tweaks.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

public class ConfigBooleanValue extends ConfigValue<Boolean>{

	public ConfigBooleanValue(boolean defaultValue, String commentKey) {
		super(defaultValue, commentKey);
	}

	@Override
	public JsonElement toJsonPrimitive() {
		return new JsonPrimitive(this.get());
	}

}
