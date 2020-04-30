package fr.smyler.mcct.gui.screens;

import fr.smyler.mcct.MCCT;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.TranslatableText;

public class MCCTConfigScreen extends Screen {
	
	private Screen parentScreen;

	public MCCTConfigScreen(Screen screen) {
		super(new TranslatableText(MCCT.MOD_ID + ".configscreen.title"));
		this.parentScreen = screen;

	}
	
	@Override
	public void init() {
		super.init();
		this.addButton(new ButtonWidget(this.width / 2 - 75, this.height - 40, 150, 20, I18n.translate("gui.done"), button ->  {
			MinecraftClient.getInstance().openScreen(this.parentScreen);
		}));
	}
	
	@Override
	public void render(int mouseX, int mouseY, float delta) {
		this.renderBackground();
		super.render(mouseX, mouseY, delta);
		this.drawCenteredString(this.font, I18n.translate(MCCT.MOD_ID + ".configscreen.title"), this.width/2, 20, 0xFFFFFF);
	}

}
