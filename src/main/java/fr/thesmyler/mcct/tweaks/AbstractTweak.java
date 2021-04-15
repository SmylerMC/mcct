package fr.thesmyler.mcct.tweaks;

import java.util.HashMap;
import java.util.Map;

import fr.thesmyler.mcct.MCCT;
import fr.thesmyler.mcct.config.ConfigBooleanValue;
import fr.thesmyler.mcct.config.ConfigValue;
import fr.thesmyler.mcct.config.InvalidConfigValue;
import fr.thesmyler.mcct.config.InvalidConfigurationException;
import net.minecraft.client.resource.language.I18n;

public abstract class AbstractTweak {
	
	protected final ConfigBooleanValue ACTIVATED;

	protected final String id;
	
	protected String displayNameKey;
	protected String longDescriptionKey;
	
	public AbstractTweak(String id, String name, String longDescription, boolean onByDefault) {
		this.id = id;
		this.ACTIVATED = new ConfigBooleanValue(onByDefault, "activated");
		this.displayNameKey = MCCT.MOD_ID + "." + name;
		this.longDescriptionKey = MCCT.MOD_ID + "." + longDescription;
		Tweaks.registerTweak(this);
	}
	
	public AbstractTweak(String id, String name, String longDescription) {
		this(id, name, longDescription, true);
	}
	
	/**
	 * 
	 * @return The current configuration as a Json serializable object 
	 */
	public abstract Map<String, ConfigValue<?>> getConfiguration();
	
	/**
	 * Just a helper function to call when implementing getConfiguration in order to get a map the the activated config value
	 * 
	 * @return A map with the "activated" key associated with the activated config value
	 */
	protected Map<String, ConfigValue<?>> getBaseConfiguration() {
		Map<String, ConfigValue<?>> config = new HashMap<String, ConfigValue<?>>();
		config.put("activated", ACTIVATED);
		return config;
	}
	
	/**
	 * Set the tweak configuration
	 * 
	 * @param configuration deserialized from json
	 * @throws InvalidConfigurationException if configuration is not a valid config for this tweak
	 * 
	 */
	public abstract void setFromConfiguration(Map<String, Object> configuration) throws InvalidConfigurationException ;
	
	public String getId() {
		return this.id;
	}
	public String getLocalizedName() {
		return I18n.translate(this.getNameKey());
	}
	
	public String getLocalizedDescription() {
		return I18n.translate(this.getLongDescriptionKey());
	}
	
	public boolean isActivated() {
		return this.ACTIVATED.get();
	}
	
	public void setActivated(boolean activated) {
		try {
			this.ACTIVATED.set(activated);
		} catch (InvalidConfigValue e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getNameKey() {
		return displayNameKey;
	}

	public void setNameKey(String key) {
		this.displayNameKey = key;
	}

	public String getLongDescriptionKey() {
		return longDescriptionKey;
	}

	public void setLongDescriptionKey(String longDescription) {
		this.longDescriptionKey = longDescription;
	}

}
