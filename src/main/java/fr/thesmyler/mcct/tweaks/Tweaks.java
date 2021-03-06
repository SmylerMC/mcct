package fr.thesmyler.mcct.tweaks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import fr.thesmyler.mcct.MCCT;
import fr.thesmyler.mcct.config.ConfigValue;
import fr.thesmyler.mcct.config.InvalidConfigurationException;
import  net.fabricmc.loader.api.FabricLoader;

public abstract class Tweaks {
	
	private static final String CONFIG_FILE_NAME = "mcct_config.json";
	
	private static final HashMap<String, AbstractTweak> tweaks = new HashMap<String, AbstractTweak>();
	
	public static final InventoryTweak INVENTORY = new InventoryTweak("inventory");
	public static final AudioDeviceTweak SOUND_DEVICE = new AudioDeviceTweak("sound_output_device");
	public static final ToastTweak TOASTS = new ToastTweak("toasts");
	public static final BugfixesTweak BUGFIXES = new BugfixesTweak("bugfixes");
	//public static final TestTweak TEST_TWEAK = new TestTweak("test");

	public static void registerTweak(AbstractTweak tweak) {
		MCCT.LOGGER.debug("Registering tweak: " + tweak.getId());
		AbstractTweak other = Tweaks.tweaks.putIfAbsent(tweak.getId(), tweak);
		if(other != null) MCCT.LOGGER.error("Trying to register a tweak which already exists: " + tweak.getId() + " Ignoring it!");
	}
	
	public static Collection<AbstractTweak> getTweaks() {
		return Tweaks.tweaks.values();
	}
	
	public static void setToDefault() {
		for(AbstractTweak tweak: tweaks.values())
			for(ConfigValue<?> value: tweak.getConfiguration().values()) value.reset();
	}
	
	public static String writeToJson() {
		Map<String, Map<String, ConfigValue<?>>> configTweaks = new HashMap<String, Map<String, ConfigValue<?>>>();
		for(String key: tweaks.keySet()) {
			configTweaks.put(key, tweaks.get(key).getConfiguration());
		}
		return ConfigValue.getSerializerGson().toJson(configTweaks);
	}
	
	public static void readFromJson(String jsonString) {
		try {
			Gson gson = ConfigValue.getSerializerGson();
			HashMap<String, HashMap<String, Object>> config = gson.fromJson(jsonString, new TypeToken<HashMap<String, HashMap<String, Object>>>() {}.getType());
			for(String key: config.keySet()) {
				if(!Tweaks.tweaks.containsKey(key)) throw new InvalidConfigurationException("Invalid tweak id");
				Tweaks.tweaks.get(key).setFromConfiguration(config.get(key));
			}
		} catch(ClassCastException e) {
			throw new InvalidConfigurationException(e);
		}
	}
	
	private static File getConfigFile() {
		Path directory = FabricLoader.getInstance().getConfigDir();
		String path = directory.toString() + File.separatorChar + CONFIG_FILE_NAME;
		return new File(path);
	}
	
	public static void readFromFile(File file) {
		try(Scanner scanner = new Scanner(file);) {
			String json = "";
			while(scanner.hasNext()) json += scanner.nextLine();
			readFromJson(json);
		} catch (FileNotFoundException e) {
			MCCT.LOGGER.error("Failed to read mcct file: " + file.getAbsolutePath());
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			MCCT.LOGGER.error("malformated json in mcct file: " + file.getAbsolutePath());
			e.printParentStackTrace();
		}
	}
	
	public static void writeToFile(File file) {
		MCCT.LOGGER.debug("Writing mcct config to file " + file.getAbsolutePath());
		try(FileWriter writer = new FileWriter(file);) {
			writer.write(writeToJson());
		} catch (IOException e) {
			MCCT.LOGGER.error("Failed to write mcct config file: " + file.getAbsolutePath());
			e.printStackTrace();
		}
	}
	
	public static void saveConfig() {
		writeToFile(getConfigFile());
	}
	
	public static void loadConfig() {
		File confFile = getConfigFile();
		if(!confFile.exists()) {
			MCCT.LOGGER.info("Creating mcct config file");
			try {
				confFile.createNewFile();
				saveConfig();
			} catch (IOException e) {
				MCCT.LOGGER.error("Failed to create mcct config file: " + confFile.getAbsolutePath());
				e.printStackTrace();
			}
		} else {
			readFromFile(confFile);
		}
		
	}

}
