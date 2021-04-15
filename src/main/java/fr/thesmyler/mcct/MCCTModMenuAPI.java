package fr.thesmyler.mcct;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

import fr.thesmyler.mcct.gui.screens.MCCTConfigScreen;

public class MCCTModMenuAPI implements ModMenuApi {
	
	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory() {
		return screen -> new MCCTConfigScreen(screen);
	}

}
