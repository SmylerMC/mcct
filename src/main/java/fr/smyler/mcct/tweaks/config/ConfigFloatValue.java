package fr.smyler.mcct.tweaks.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

public class ConfigFloatValue extends ConfigNumericValue<Float> {

	public ConfigFloatValue(float defaultValue, float minValue, float maxValue, String commentKey) {
		super(defaultValue, minValue, maxValue, commentKey);
	}
	
	@Override
	public JsonElement toJsonPrimitive() {
		return new JsonPrimitive(this.get());
	}

}
