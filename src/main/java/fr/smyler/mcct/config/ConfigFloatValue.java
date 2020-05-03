package fr.smyler.mcct.config;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class ConfigFloatValue extends ConfigNumericValue<Float> {

	public ConfigFloatValue(float defaultValue, float minValue, float maxValue, String commentKey) {
		super(defaultValue, minValue, maxValue, commentKey);
	}
	
	@Override
	public JsonElement toJsonPrimitive() {
		return new JsonPrimitive(this.get());
	}

}

class ConfigFloatValueSerializer implements JsonSerializer<ConfigFloatValue>{

	@Override
	public JsonElement serialize(ConfigFloatValue src, Type typeOfSrc, JsonSerializationContext context) {
		return src.toJsonPrimitive();
	}

}