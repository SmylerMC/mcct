package fr.thesmyler.mcct.config;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class ConfigStringValue extends ConfigValue<String> {

	public ConfigStringValue(String defaultValue, String commentKey) {
		super(defaultValue, commentKey);
	}

	@Override
	public JsonElement toJsonPrimitive() {
		return new JsonPrimitive(this.get());
	}

}

class ConfigStringValueSerializer implements JsonSerializer<ConfigStringValue>{

	@Override
	public JsonElement serialize(ConfigStringValue src, Type typeOfSrc, JsonSerializationContext context) {
		return src.toJsonPrimitive();
	}

}