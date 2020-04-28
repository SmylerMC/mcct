package fr.smyler.mcct.tweaks;

import java.util.Collection;
import java.util.HashMap;

import fr.smyler.mcct.MCCT;

public abstract class Tweaks {
	
	private static HashMap<String, AbstractTweak> tweaks = new HashMap<String, AbstractTweak>();
	
	
	
	public static void registerTweak(AbstractTweak tweak) {
		MCCT.LOGGER.debug("Registering tweak: " + tweak.getId());
		AbstractTweak other = Tweaks.tweaks.putIfAbsent(tweak.getId(), tweak);
		if(other != null) MCCT.LOGGER.error("Trying to register a tweak which already exists: " + tweak.getId() + " Ignoring it!");
	}
	
	public static Collection<AbstractTweak> getTweaks() {
		return Tweaks.tweaks.values();
	}

}
