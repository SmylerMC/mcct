package fr.thesmyler.mcct.tweaks;

import java.util.HashMap;
import java.util.Map;

import fr.thesmyler.mcct.config.ConfigBooleanValue;
import fr.thesmyler.mcct.config.ConfigValue;
import fr.thesmyler.mcct.config.InvalidConfigurationException;
import net.minecraft.util.Util;

public class BugfixesTweak extends AbstractTweak {
	
	public final ConfigBooleanValue LINUX_OPENFILE = new ConfigBooleanValue(Util.getOperatingSystem() == Util.OperatingSystem.LINUX, "openfile_linux_bugfix");

	public BugfixesTweak(String id) {
		super(id, "bugfixes_tweak.name", "bugfixes_tweak.desc");
	}

	@Override
	public Map<String, ConfigValue<?>> getConfiguration() {
		Map<String, ConfigValue<?>> config = new HashMap<>();
		config.put(LINUX_OPENFILE.name(), LINUX_OPENFILE);
		return config;
	}

	@Override
	public void setFromConfiguration(Map<String, Object> configuration) throws InvalidConfigurationException {
		LINUX_OPENFILE.set((Boolean)configuration.getOrDefault(LINUX_OPENFILE.name(), LINUX_OPENFILE.getDefault()));
	}

}
