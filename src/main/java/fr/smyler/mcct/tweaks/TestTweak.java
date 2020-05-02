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
		conf.put("boolVal", this.aBooleanValue2);
		conf.put("boolVal", this.aBooleanValue3);
		conf.put("boolVal", this.aBooleanValue4);
		conf.put("boolVal", this.aBooleanValue5);
		conf.put("boolVal", this.aBooleanValue6);
		conf.put("boolVal", this.aBooleanValue7);
		conf.put("boolVal", this.aBooleanValue8);
		return conf;
	}

	@Override
	public void setFromConfiguration(HashMap<String, Object> configuration) throws InvalidConfigurationException {
		// TODO testTweak setFromConfiguration
	}

}
