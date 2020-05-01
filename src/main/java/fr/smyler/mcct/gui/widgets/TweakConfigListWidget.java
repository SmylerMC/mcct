package fr.smyler.mcct.gui.widgets;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import fr.smyler.mcct.MCCT;
import fr.smyler.mcct.tweaks.AbstractTweak;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.widget.ElementListWidget;
import net.minecraft.client.gui.widget.ToggleButtonWidget;
import net.minecraft.util.Identifier;

public class TweakConfigListWidget extends ElementListWidget<TweakConfigListWidget.TweakEntry> {

	public TweakConfigListWidget(MinecraftClient client, int width, int height, int top, int bottom, int itemHeight) {
		super(client, width, height, top, bottom, itemHeight);
	}

	public void addTweakEntry(AbstractTweak tweak) {
		this.addEntry(new TweakEntry(tweak));
	}

	public void addAll(AbstractTweak[] tweaks) {
		for(int i = 0; i < tweaks.length; i += 2) {
			this.addTweakEntry(tweaks[i]);
		}
	}

	public void addAll(Collection<AbstractTweak> tweaks) {
		for(AbstractTweak tweak: tweaks) {
			this.addTweakEntry(tweak);
		}
	}

	@Override
	public int getRowWidth() {
		return 400;
	}

	@Override
	protected int getScrollbarPosition() {
		return super.getScrollbarPosition() + 32;
	}

	public class TweakEntry extends ElementListWidget.Entry<TweakConfigListWidget.TweakEntry> {

		private final AbstractTweak tweak;
		private final TextRenderer font = MinecraftClient.getInstance().textRenderer;
		private ToggleButtonWidget toggleButton;

		public TweakEntry(AbstractTweak tweak) {
			this.tweak = tweak;
			this.toggleButton = new ToggleButtonWidget(0, 0, 26, 16, true) {

				@Override
				public void onClick(double mouseX, double mouseY) {
					if(this.active) this.toggled = !this.toggled;
				}

			};
			this.toggleButton.setTextureUV(2, 2, 28, 18, new Identifier(MCCT.MOD_ID, "textures/gui/widgets.png"));
		}

		@Override
		public void render(int index, int y, int width, int l, int m, int mouseX, int mouseY, boolean mouseOver, float delta) {
			this.toggleButton.x = 200;
			this.toggleButton.y = y;
			this.toggleButton.render(mouseX, mouseY, delta);
			TweakConfigListWidget.this.drawString(this.font, this.tweak.getLocalizedName(), 10, y + 5, 0xFFFFFF);
		}

		@Override
		public List<? extends Element> children() {
			List<Element> children = new ArrayList<Element>();
			children.add(this.toggleButton);
			return children;
		}
	}
}
