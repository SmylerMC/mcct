package fr.smyler.mcct.tweaks;

import java.util.HashMap;

import fr.smyler.mcct.config.ConfigBooleanValue;
import fr.smyler.mcct.config.ConfigFloatValue;
import fr.smyler.mcct.config.ConfigIntValue;
import fr.smyler.mcct.config.ConfigStringValue;
import fr.smyler.mcct.config.ConfigValue;
import fr.smyler.mcct.config.InvalidConfigurationException;

/**
 * Only exists for test purposes
 * 
 * @author SmylerMC
 *
 */
public class TestTweak extends AbstractTweak {
	
	public final ConfigBooleanValue aBooleanValue1 = new ConfigBooleanValue(true, "mcct.testtweak.booleancomment");
	public final ConfigBooleanValue aBooleanValue2 = new ConfigBooleanValue(true, "mcct.testtweak.booleancomment");
	public final ConfigBooleanValue aBooleanValue3 = new ConfigBooleanValue(true, "mcct.testtweak.booleancomment");
	public final ConfigBooleanValue aBooleanValue4 = new ConfigBooleanValue(true, "mcct.testtweak.booleancomment");
	public final ConfigBooleanValue aBooleanValue5 = new ConfigBooleanValue(true, "mcct.testtweak.booleancomment");
	public final ConfigBooleanValue aBooleanValue6 = new ConfigBooleanValue(true, "mcct.testtweak.booleancomment");
	public final ConfigBooleanValue aBooleanValue7 = new ConfigBooleanValue(true, "mcct.testtweak.booleancomment");
	public final ConfigBooleanValue aBooleanValue8 = new ConfigBooleanValue(true, "mcct.testtweak.booleancomment");
	public final ConfigIntValue anIntValue = new ConfigIntValue(10, 0, 100, "mcct.testtweak.intcomment");
	public final ConfigFloatValue aFloatValue = new ConfigFloatValue(10, 0, 100, "mcct.testtweak.floatcomment");
	public final ConfigStringValue aStringValue = new ConfigStringValue("default_value", "mcct.testtweak.strincomment");

	public TestTweak(String id) {
		super(id, "testtweak.name", "testtweak.desc");
	}

	@Override
	public boolean hasConfiguration() {
		return true;
	}

	@Override
	public HashMap<String, ConfigValue<?>> getConfiguration() {
		HashMap<String, ConfigValue<?>> conf = new HashMap<String, ConfigValue<?>>();
		conf.put("activated", this.activated);
		conf.put("boolVal", this.aBooleanValue1);
		conf.put("intVal", this.anIntValue);
		conf.put("floatVal", this.aFloatValue);
		conf.put("stringVal", this.aStringValue);
		conf.put("boolVal2", this.aBooleanValue2);
		conf.put("boolVal3", this.aBooleanValue3);
		conf.put("boolVal4", this.aBooleanValue4);
		conf.put("boolVal5", this.aBooleanValue5);
		conf.put("boolVal6", this.aBooleanValue6);
		conf.put("boolVal7", this.aBooleanValue7);
		conf.put("boolVal8", this.aBooleanValue8);
		return conf;
	}

	@Override
	public void setFromConfiguration(HashMap<String, Object> configuration) throws InvalidConfigurationException {
		this.activated.set((boolean)configuration.get("activated"));
		this.aBooleanValue1.set((boolean)configuration.get("boolVal"));
		this.aBooleanValue2.set((boolean)configuration.get("boolVal2"));
		this.aBooleanValue3.set((boolean)configuration.get("boolVal3"));
		this.aBooleanValue4.set((boolean)configuration.get("boolVal4"));
		this.aBooleanValue5.set((boolean)configuration.get("boolVal5"));
		this.aBooleanValue6.set((boolean)configuration.get("boolVal6"));
		this.aBooleanValue7.set((boolean)configuration.get("boolVal7"));
		this.aBooleanValue8.set((boolean)configuration.get("boolVal8"));
		this.anIntValue.set(((Double)configuration.get("intVal")).intValue());
		this.aFloatValue.set(((Double)configuration.get("floatVal")).floatValue());
		this.aStringValue.set((String)configuration.get("stringVal"));
	}

}
