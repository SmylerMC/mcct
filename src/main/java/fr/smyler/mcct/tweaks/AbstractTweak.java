package fr.smyler.mcct.tweaks;

import fr.smyler.mcct.MCCT;
import net.minecraft.client.resource.language.I18n;

public abstract class AbstractTweak {
	
	protected boolean activated;

	protected String id;
	
	protected String displayNameKey;
	protected String longDescriptionKey;
	
	public AbstractTweak(String id, String name, String longDescription) {
		this.id = id;
		this.activated = false;
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
	public abstract Object getConfiguration();
	
	/**
	 * Set the tweak configuration
	 * 
	 * @param configuration configuration object deserialized from json
	 * @throws InvalidConfiguration if configuration is not a valid config for thos tweak
	 * 
	 */
	public abstract void setFromConfiguration(Object configuration) throws InvalidConfiguration;
	
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
		return this.activated;
	}
	
	public void setActivated(boolean activated) {
		this.activated = activated;
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
