package fr.smyler.mcct.tweaks;

import java.util.HashMap;

import fr.smyler.mcct.MCCT;
import fr.smyler.mcct.config.ConfigBooleanValue;
import fr.smyler.mcct.config.ConfigValue;
import fr.smyler.mcct.config.InvalidConfigValue;
import fr.smyler.mcct.config.InvalidConfigurationException;
import net.minecraft.client.resource.language.I18n;

public abstract class AbstractTweak {
	
	protected final ConfigBooleanValue activated;

	protected final String id;
	
	protected String displayNameKey;
	protected String longDescriptionKey;
	
	public AbstractTweak(String id, String name, String longDescription) {
		this.id = id;
		this.activated = new ConfigBooleanValue(true, "activated");
		this.displayNameKey = MCCT.MOD_ID + "." + name;
		this.longDescriptionKey = MCCT.MOD_ID + "." + longDescription;
		Tweaks.registerTweak(this);
	}
	
	/**
	 * Should return true if the tweak has any configuration other than its activation state
	 * 
	 * @return true if the tweak has any configuration other than its activation state
	 */
	public abstract boolean hasConfiguration();
	
	/**
	 * 
	 * @return The current configuration as a Json serializable object 
	 */
	public abstract HashMap<String, ConfigValue<?>> getConfiguration();
	
	/**
	 * Set the tweak configuration
	 * 
	 * @param configuration deserialized from json
	 * @throws InvalidConfigurationException if configuration is not a valid config for this tweak
	 * 
	 */
	public abstract void setFromConfiguration(HashMap<String, Object> configuration) throws InvalidConfigurationException ;
	
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
		return this.activated.get();
	}
	
	public void setActivated(boolean activated) {
		try {
			this.activated.set(activated);
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
