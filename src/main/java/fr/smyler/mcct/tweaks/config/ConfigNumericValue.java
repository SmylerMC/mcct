package fr.smyler.mcct.tweaks.config;

public abstract class ConfigNumericValue<T extends Comparable<T>> extends ConfigValue<T> {
	
	private final T minValue, maxValue;
	
	public ConfigNumericValue(T defaultValue, T minValue, T maxValue, String commentKey) {
		super(defaultValue, commentKey);
		this.minValue = minValue;
		this.maxValue = maxValue;
	}

	@Override
	public void checkValue(T value) throws InvalidConfigValue{
		if(value.compareTo(this.getMin()) < 0)
			throw new InvalidConfigValue("The given value is small than min");
		if(value.compareTo(this.getMax()) > 0)
			throw new InvalidConfigValue("The given value is greater than max");
	}
	
	public T getMax() {
		return this.maxValue;
	}
	
	public T getMin() {
		return this.minValue;
	}
	
}
