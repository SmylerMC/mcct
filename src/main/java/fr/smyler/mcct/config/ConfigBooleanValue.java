package fr.smyler.mcct.config;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class ConfigBooleanValue extends ConfigValue<Boolean>{

	public ConfigBooleanValue(boolean defaultValue, String commentKey) {
		super(defaultValue, commentKey);
	}

	@Override
	public JsonElement toJsonPrimitive() {
		return new JsonPrimitive(this.get());
	}

}

class ConfigBooleanValueSerializer implements JsonSerializer<ConfigBooleanValue>{

	@Override
	public JsonElement serialize(ConfigBooleanValue src, Type typeOfSrc, JsonSerializationContext context) {
		return src.toJsonPrimitive();
	}

}
