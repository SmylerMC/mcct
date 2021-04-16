package fr.thesmyler.mcct.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import net.minecraft.client.resource.language.I18n;

public abstract class ConfigValue<T> {

	private T value;
	private final T defaultValue;
	private final String name;
	private static final Gson GSON = new GsonBuilder()
			.registerTypeAdapter(ConfigBooleanValue.class, new ConfigBooleanValueSerializer())
			.registerTypeAdapter(ConfigIntValue.class, new ConfigIntValueSerializer())
			.registerTypeAdapter(ConfigFloatValue.class, new ConfigFloatValueSerializer())
			.registerTypeAdapter(ConfigStringValue.class, new ConfigStringValueSerializer())
			.setPrettyPrinting().create();

	public ConfigValue(T defaultValue, String commentKey) {
		this.checkValue(defaultValue);
		this.value = defaultValue;
		this.defaultValue = defaultValue;
		this.name = commentKey;
	}

	/**
	 * Should throw an InvalidConfigurationException if value is not a valid value
	 * 
	 * @param value
	 */
	public void checkValue(T value) {}

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
	
	public void reset() {
		this.value = this.defaultValue;
	}

	public String getLocalizedComment() {
		return I18n.translate(this.name);
	}
	
	public String name() {
		return this.name;
	}
	
	public static Gson getSerializerGson() {
		return GSON;
	}

}