package fr.thesmyler.mcct;

import org.lwjgl.glfw.GLFW;

import fr.thesmyler.mcct.gui.screens.MCCTConfigScreen;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

public abstract class MCCTKeyBindings {
	
	private final static String CATEGORY_NAME = MCCT.MOD_ID + ".bindings";

	private final static KeyBinding OPEN_CONFIG_BINDING = new KeyBinding("key.mcct.openconfig", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_J, CATEGORY_NAME);
	
	public static void registerBindings() {
		KeyBindingHelper.registerKeyBinding(OPEN_CONFIG_BINDING);
	}
	
	public static void checkBindings(MinecraftClient client) {
		if(OPEN_CONFIG_BINDING.isPressed()) MCCTConfigScreen.openNew();
	}
}
