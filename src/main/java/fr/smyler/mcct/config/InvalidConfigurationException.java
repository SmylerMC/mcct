package fr.smyler.mcct.config;

public class InvalidConfigurationException extends IllegalArgumentException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4969233821010045465L;
	
	public InvalidConfigurationException(String message) {
		super(message);
	}
	
	public InvalidConfigurationException() {
		super();
	}

}
