package fr.thesmyler.mcct.gui.screens;

import java.util.Map;

import fr.thesmyler.mcct.MCCT;
import fr.thesmyler.mcct.config.ConfigValue;
import fr.thesmyler.mcct.gui.RenderUtils;
import fr.thesmyler.mcct.gui.widgets.ConfigValueListWidget;
import fr.thesmyler.mcct.gui.widgets.ConfigValueListWidget.ValueEntry;
import fr.thesmyler.mcct.gui.widgets.TweakConfigListWidget;
import fr.thesmyler.mcct.gui.widgets.TweakConfigListWidget.TweakEntry;
import fr.thesmyler.mcct.tweaks.AbstractTweak;
import fr.thesmyler.mcct.tweaks.Tweaks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.TranslatableText;

public class MCCTConfigScreen extends Screen {

	private Screen parentScreen;
	private TweakConfigListWidget widgetTweakList;
	private ConfigValueListWidget widgetValueList;
	private ButtonWidget resetValuesButton;

	public MCCTConfigScreen(Screen screen) {
		super(new TranslatableText(MCCT.MOD_ID + ".configscreen.title"));
		this.parentScreen = screen;
	}

	@Override
	public void init() {
		super.init();
		ButtonWidget resetButton = new ButtonWidget(this.width / 2 - 155, this.height - 28, 150, 20, new TranslatableText(MCCT.MOD_ID + ".configscreen.default"), button ->  {
			Tweaks.setToDefault();
			this.widgetTweakList.updateFromTweaks();
			this.widgetValueList.updateFromTweaks();
		});
		this.widgetTweakList = new TweakConfigListWidget(this.client, this.width / 2 - 10, this.height - 30, 50, this.height - 50, 30, (oldEntry, newEntry) -> {
			MCCTConfigScreen.this.widgetValueList.clear();
			Map<String, ConfigValue<?>> config = newEntry.getTweak().getConfiguration();
			config.remove("activated");
			this.resetValuesButton.active = config.size() > 0;
			MCCTConfigScreen.this.widgetValueList.addAll(config);
			MCCTConfigScreen.this.widgetValueList.setScrollAmount(0);
		});
		this.widgetTweakList.setLeftPos(5);
		this.widgetValueList = new ConfigValueListWidget(this.client, this.width / 2 - 10, this.height, this.height / 2, this.height - 50, 55);
		this.widgetValueList.setLeftPos(this.width/2 + 5);
		this.addButton(resetButton);
		this.addButton(new ButtonWidget(this.width / 2 + 4, this.height - 28, 150, 20, new TranslatableText("gui.done"), button ->  {
			Tweaks.saveConfig();
			MinecraftClient.getInstance().openScreen(this.parentScreen);
		}));
		this.resetValuesButton = new ButtonWidget(this.width / 4 * 3 - 100, this.height / 2 - 25, 200, 20, new TranslatableText(MCCT.MOD_ID + ".configscreen.valuesdefault"), button ->  {
			for(ValueEntry<?> entry: this.widgetValueList.children()) {
				entry.resetValue();
			}
		});
		this.resetValuesButton.active = false;
		this.addButton(resetValuesButton);
		this.widgetTweakList.addAll(Tweaks.getTweaks());
		this.children.add(this.widgetTweakList);
		this.children.add(this.widgetValueList);
	}

	@Override
	public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
		this.renderBackgroundTexture(0);
		this.widgetTweakList.render(matrices, mouseX, mouseY, delta);
		this.widgetValueList.render(matrices, mouseX, mouseY, delta);
		DrawableHelper.drawCenteredString(matrices, this.textRenderer, I18n.translate(MCCT.MOD_ID + ".configscreen.title"), this.width/2, 20, 0xFFFFFF);
		DrawableHelper.drawCenteredString(matrices, this.textRenderer, I18n.translate(MCCT.MOD_ID + ".configscreen.tweakconfig"), this.width/4 * 3, 50, 0xFFFFFF);
		TweakEntry entry = this.widgetTweakList.getSelected();
		if(entry != null) {
			AbstractTweak tweak = entry.getTweak();
			DrawableHelper.drawCenteredString(matrices, this.textRenderer, tweak.getLocalizedName(), this.width/4 * 3, 65, 0xFFFFFF);
			RenderUtils.drawMultilineBoxedString(this.client, matrices, tweak.getLocalizedDescription(), this.width/2 + 5, 80, this.width / 2 -10, 0xBBBBBB);

		} else {
			RenderUtils.drawMultilineBoxedString(this.client, matrices, I18n.translate(MCCT.MOD_ID + ".configscreen.selecttweak"), this.width/2 + 5, 70, this.width / 2 -10, 0xBBBBBB);
		}
		super.render(matrices, mouseX, mouseY, delta);
	}
	
	public static void openNew() {
		MinecraftClient mc = MinecraftClient.getInstance();
		mc.openScreen(new MCCTConfigScreen(mc.currentScreen));
	}
	
}