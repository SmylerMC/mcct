package fr.smyler.mcct.gui.screens;

import fr.smyler.mcct.MCCT;
import fr.smyler.mcct.gui.widgets.TweakConfigListWidget;
import fr.smyler.mcct.tweaks.Tweaks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.TranslatableText;

public class MCCTConfigScreen extends Screen {

	private Screen parentScreen;
	private TweakConfigListWidget widgetList;

	public MCCTConfigScreen(Screen screen) {
		super(new TranslatableText(MCCT.MOD_ID + ".configscreen.title"));
		this.parentScreen = screen;
	}

	@Override
	public void init() {
		super.init();
		ButtonWidget resetButton = new ButtonWidget(this.width / 2 - 155, this.height - 28, 150, 20, I18n.translate(MCCT.MOD_ID + ".configscreen.default"), button ->  {
			Tweaks.setToDefault();
		});
		resetButton.active = false;
		this.addButton(resetButton);
		this.addButton(new ButtonWidget(this.width / 2 + 4, this.height - 28, 150, 20, I18n.translate("gui.done"), button ->  {
			//TODO Save config to file
			MinecraftClient.getInstance().openScreen(this.parentScreen);
		}));
		this.widgetList = new TweakConfigListWidget(minecraft, this.width / 2, this.height - 30, 50, this.height - 50, 30);
		for(int j=0; j< 100; j++) this.widgetList.addAll(Tweaks.getTweaks());
		this.children.add(this.widgetList);
	}

	@Override
	public void render(int mouseX, int mouseY, float delta) {
		this.renderDirtBackground(0);
		super.render(mouseX, mouseY, delta);
		this.widgetList.render(mouseX, mouseY, delta);
		this.drawCenteredString(this.font, I18n.translate(MCCT.MOD_ID + ".configscreen.title"), this.width/2, 20, 0xFFFFFF);
	}
	
}