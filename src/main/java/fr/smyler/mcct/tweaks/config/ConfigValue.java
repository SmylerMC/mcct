package fr.smyler.mcct.tweaks.config;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import net.minecraft.client.resource.language.I18n;

public abstract class ConfigValue<T> {

	private T value;
	private final T defaultValue;
	private final String commentKey;

	public ConfigValue(T defaultValue, String commentKey) {
		this.value = defaultValue;
		this.defaultValue = defaultValue;
		this.commentKey = commentKey;
	}

	public void checkValue(T value) throws InvalidConfigValue {}

	public abstract JsonElement toJsonPrimitive();

	public T get() {
		return this.value;
	}

	public T getDefault() {
		return this.defaultValue;
	}

	public void set(T value) throws InvalidConfigValue {
		this.checkValue(value);
		this.value = value;
	}

	public String getLocalizedComment() {
		return I18n.translate(this.commentKey);
	}

}

class ConfigValueSerializer implements JsonSerializer<ConfigValue<?>>{

	@Override
	public JsonElement serialize(ConfigValue<?> src, Type typeOfSrc, JsonSerializationContext context) {
		return src.toJsonPrimitive();
	}


}
