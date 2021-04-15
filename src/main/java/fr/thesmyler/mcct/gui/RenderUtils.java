package fr.thesmyler.mcct.gui;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.math.MatrixStack;
public abstract class RenderUtils {
	
	public static void drawMultilineBoxedString(MinecraftClient minecraft, MatrixStack matrices, String text, int x, int y, int width, int color) {
		TextRenderer textRenderer = minecraft.textRenderer;
		int pos = y;
		String[] splited = text.split(" "); //TODO smarter line warping, this won't work with some languages
		List<String> segments = new ArrayList<String>();
		for(String s: splited) segments.add(s);
		while(segments.size() > 0) {
			String dispString = segments.get(0);
			int i=1;
			for(; i < segments.size() && textRenderer.getWidth(dispString + segments.get(i)) < width; i++) {
				dispString += " " + segments.get(i);
			}
			for(int k=0; k<i; k++) segments.remove(0);
			textRenderer.draw(matrices, dispString, x, pos, color);
			pos += textRenderer.fontHeight * 1.5;
		}
	}

}
