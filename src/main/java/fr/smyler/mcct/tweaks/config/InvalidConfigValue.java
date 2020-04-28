package fr.smyler.mcct.tweaks.config;

public class InvalidConfigValue extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4539832264354343396L;
	
	public InvalidConfigValue(String message) {
		super(message);
	}
	
	public InvalidConfigValue() {
		super();
	}

}
