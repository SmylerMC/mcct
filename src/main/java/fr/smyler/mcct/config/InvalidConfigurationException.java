package fr.smyler.mcct.config;

public class InvalidConfigurationException extends IllegalArgumentException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4969233821010045465L;
	private final Exception parent;
	
	public InvalidConfigurationException(String message) {
		super(message);
		this.parent = null;
	}
	
	public InvalidConfigurationException() {
		super();
		this.parent = null;
	}
	
	public InvalidConfigurationException(Exception e) {
		super();
		this.parent = e;
	}
	
	public void printParentStackTrace() {
		if(this.parent != null) this.parent.printStackTrace();
	}

}
