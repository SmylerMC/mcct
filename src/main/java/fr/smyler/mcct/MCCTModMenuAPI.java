package fr.smyler.mcct;

import fr.smyler.mcct.gui.screens.MCCTConfigScreen;
import io.github.prospector.modmenu.api.ConfigScreenFactory;
import io.github.prospector.modmenu.api.ModMenuApi;

public class MCCTModMenuAPI implements ModMenuApi {

	@Override
	public String getModId() {
		return MCCT.MOD_ID;
	}
	
	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory() {
		return screen -> new MCCTConfigScreen(screen);
	}

}
