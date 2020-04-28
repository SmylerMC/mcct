package fr.smyler.mcct.tweaks;

public class BasicTweak extends AbstractTweak {

	public BasicTweak(String id, String name, String longDescription) {
		super(id, name, longDescription);
	}

	@Override
	public boolean hasConfiguration() {
		return false;
	}

	@Override
	public Object getConfiguration() {
		return this.isActivated();
	}

	@Override
	public void setFromConfiguration(Object configuration) throws InvalidConfiguration {
		this.setActivated((Boolean)configuration);
	}

}
