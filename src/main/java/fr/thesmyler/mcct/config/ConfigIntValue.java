package fr.thesmyler.mcct.config;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class ConfigIntValue extends ConfigNumericValue<Integer> {

	public ConfigIntValue(int defaultValue, int minValue, int maxValue, String commentKey) {
		super(defaultValue, minValue, maxValue, commentKey);
	}

	@Override
	public JsonElement toJsonPrimitive() {
		return new JsonPrimitive(this.get());
	}

}

class ConfigIntValueSerializer implements JsonSerializer<ConfigIntValue>{

	@Override
	public JsonElement serialize(ConfigIntValue src, Type typeOfSrc, JsonSerializationContext context) {
		return src.toJsonPrimitive();
	}

}