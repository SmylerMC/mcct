package fr.smyler.mcct.gui.screens;

import java.util.Map;

import fr.smyler.mcct.MCCT;
import fr.smyler.mcct.config.ConfigValue;
import fr.smyler.mcct.gui.RenderUtils;
import fr.smyler.mcct.gui.widgets.ConfigValueListWidget;
import fr.smyler.mcct.gui.widgets.TweakConfigListWidget;
import fr.smyler.mcct.gui.widgets.TweakConfigListWidget.TweakEntry;
import fr.smyler.mcct.tweaks.AbstractTweak;
import fr.smyler.mcct.tweaks.Tweaks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.TranslatableText;

public class MCCTConfigScreen extends Screen {

	private Screen parentScreen;
	private TweakConfigListWidget widgetTweakList;
	private ConfigValueListWidget widgetValueList;

	public MCCTConfigScreen(Screen screen) {
		super(new TranslatableText(MCCT.MOD_ID + ".configscreen.title"));
		this.parentScreen = screen;
	}

	@Override
	public void init() {
		super.init();
		ButtonWidget resetButton = new ButtonWidget(this.width / 2 - 155, this.height - 28, 150, 20, I18n.translate(MCCT.MOD_ID + ".configscreen.default"), button ->  {
			Tweaks.setToDefault();
			this.widgetTweakList.updateFromTweaks();
			this.widgetValueList.updateFromTweaks();
		});
//		resetButton.active = false;
		this.widgetTweakList = new TweakConfigListWidget(minecraft, this.width / 2 - 10, this.height - 30, 50, this.height - 50, 30, (oldEntry, newEntry) -> {
			MCCTConfigScreen.this.widgetValueList.clear();
			Map<String, ConfigValue<?>> config = newEntry.getTweak().getConfiguration();
			config.remove("activated");
			MCCTConfigScreen.this.widgetValueList.addAll(config);
		});
		this.widgetTweakList.setLeftPos(5);
		this.widgetValueList = new ConfigValueListWidget(minecraft, this.width / 2 - 10, this.height, this.height / 2, this.height - 50, 55);
		this.widgetValueList.setLeftPos(this.width/2 + 5);
		this.addButton(resetButton);
		this.addButton(new ButtonWidget(this.width / 2 + 4, this.height - 28, 150, 20, I18n.translate("gui.done"), button ->  {
			//TODO Save config to file
			MinecraftClient.getInstance().openScreen(this.parentScreen);
		}));
		this.widgetTweakList.addAll(Tweaks.getTweaks());
		this.children.add(this.widgetTweakList);
		this.children.add(this.widgetValueList);
	}

	@Override
	public void render(int mouseX, int mouseY, float delta) {
		this.renderDirtBackground(0);
		this.widgetTweakList.render(mouseX, mouseY, delta);
		this.widgetValueList.render(mouseX, mouseY, delta);
		this.drawCenteredString(this.font, I18n.translate(MCCT.MOD_ID + ".configscreen.title"), this.width/2, 20, 0xFFFFFF);
		this.drawCenteredString(this.font, I18n.translate(MCCT.MOD_ID + ".configscreen.tweakconfig"), this.width/4 * 3, 50, 0xFFFFFF);
		TweakEntry entry = this.widgetTweakList.getSelected();
		if(entry != null) {
			AbstractTweak tweak = entry.getTweak();
			this.drawCenteredString(this.font, tweak.getLocalizedName(), this.width/4 * 3, 65, 0xFFFFFF);
			RenderUtils.drawMultilineBoxedString(this.minecraft, tweak.getLocalizedDescription(), this.width/2 + 5, 80, this.width / 2 -10, 0xBBBBBB);

		} else {
			RenderUtils.drawMultilineBoxedString(this.minecraft, I18n.translate(MCCT.MOD_ID + ".configscreen.selecttweak"), this.width/2 + 5, 70, this.width / 2 -10, 0xBBBBBB);
		}
		super.render(mouseX, mouseY, delta);
	}
	
}