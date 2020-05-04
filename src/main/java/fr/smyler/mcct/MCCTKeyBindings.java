package fr.smyler.mcct;

import org.lwjgl.glfw.GLFW;

import fr.smyler.mcct.gui.screens.MCCTConfigScreen;
import net.fabricmc.fabric.api.client.keybinding.FabricKeyBinding;
import net.fabricmc.fabric.api.client.keybinding.KeyBindingRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;

public abstract class MCCTKeyBindings {
	
	private static String CATEGORY_NAME = MCCT.MOD_ID + ".bindings";

	private static FabricKeyBinding openConfigBinding;
	
	public static void initBindings() {
		KeyBindingRegistry.INSTANCE.addCategory(CATEGORY_NAME);
		openConfigBinding = FabricKeyBinding.Builder.create(
	            new Identifier(MCCT.MOD_ID, "openconfig"),
	            InputUtil.Type.KEYSYM,
	            GLFW.GLFW_KEY_J,
	            MCCTKeyBindings.CATEGORY_NAME
	        ).build();
	}
	
	public static void registerBindings() {
		KeyBindingRegistry.INSTANCE.register(openConfigBinding);
	}
	
	public static void checkBindings(MinecraftClient client) {
		if(openConfigBinding.isPressed()) MCCTConfigScreen.openNew();
	}
}
