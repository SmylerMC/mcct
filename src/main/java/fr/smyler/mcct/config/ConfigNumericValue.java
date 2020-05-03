package fr.smyler.mcct.config;

public abstract class ConfigNumericValue<T extends Comparable<T>> extends ConfigValue<T> {
	
	private final T minValue, maxValue;
	
	public ConfigNumericValue(T defaultValue, T minValue, T maxValue, String commentKey) {
		super(defaultValue, commentKey);
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.checkValue(this.getDefault());
	}

	@Override
	public void checkValue(T value) {
		if(this.getMin() != null && value.compareTo(this.getMin()) < 0)
			throw new InvalidConfigValue("The given value is small than min");
		if(this.getMax() != null && value.compareTo(this.getMax()) > 0)
			throw new InvalidConfigValue("The given value is greater than max");
	}
	
	public T getMax() {
		return this.maxValue;
	}
	
	public T getMin() {
		return this.minValue;
	}
	
}
