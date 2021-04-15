package fr.thesmyler.mcct.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

public class ConfigNullValue extends ConfigValue<Object>{
	
	public ConfigNullValue(String commentKey) {
		super(null, commentKey);
	}

	@Override
	public JsonElement toJsonPrimitive() {
		return new JsonPrimitive(0).getAsJsonNull();
	}

}
