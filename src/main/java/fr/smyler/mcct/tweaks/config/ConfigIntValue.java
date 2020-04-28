package fr.smyler.mcct.tweaks.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

public class ConfigIntValue extends ConfigNumericValue<Integer> {

	public ConfigIntValue(int defaultValue, int minValue, int maxValue, String commentKey) {
		super(defaultValue, minValue, maxValue, commentKey);
	}

	@Override
	public JsonElement toJsonPrimitive() {
		return new JsonPrimitive(this.get());
	}

}
