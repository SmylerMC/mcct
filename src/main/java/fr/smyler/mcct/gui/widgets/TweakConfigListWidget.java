package fr.smyler.mcct.gui.widgets;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.mojang.blaze3d.systems.RenderSystem;

import fr.smyler.mcct.MCCT;
import fr.smyler.mcct.tweaks.AbstractTweak;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ElementListWidget;
import net.minecraft.client.gui.widget.ToggleButtonWidget;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormats;
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
		return this.width;
	}

	@Override
	protected int getScrollbarPosition() {
		return this.width - 5;
	}

	public class TweakEntry extends ElementListWidget.Entry<TweakConfigListWidget.TweakEntry> {

		private final AbstractTweak tweak;
		private final TextRenderer font = MinecraftClient.getInstance().textRenderer;
		private ToggleButtonWidget toggleButton;
		private ButtonWidget mainButton;

		public TweakEntry(AbstractTweak tweak) {
			this.tweak = tweak;
			this.toggleButton = new ToggleButtonWidget(0, 0, 26, 16, this.tweak.isActivated()) {

				@Override
				public void onClick(double mouseX, double mouseY) {
					if(this.active) {
						this.toggled = !this.toggled;
						TweakEntry.this.tweak.setActivated(this.isToggled());
					}
				}

			};
			this.toggleButton.setTextureUV(2, 2, 28, 18, new Identifier(MCCT.MOD_ID, "textures/gui/widgets.png"));
			this.mainButton = new ButtonWidget(0, 0, TweakConfigListWidget.this.width, TweakConfigListWidget.this.itemHeight, "", button ->  {
				TweakConfigListWidget.this.setSelected(TweakEntry.this);
			});
		}

		@Override
		public void render(int index, int y, int k, int l, int m, int mouseX, int mouseY, boolean mouseOver, float delta) {
			int x = TweakConfigListWidget.this.left;
			int width = TweakConfigListWidget.this.width;
			int height = TweakConfigListWidget.this.itemHeight;
			int color = mouseOver ? 0xBBBBFF: 0xFFFFFF;
			if(TweakConfigListWidget.this.getSelected() == this) {
				Tessellator tessellator = Tessellator.getInstance();
				BufferBuilder bufferBuilder = tessellator.getBuffer();
				RenderSystem.enableBlend();
				RenderSystem.shadeModel(7425);
				RenderSystem.disableTexture();
				bufferBuilder.begin(7, VertexFormats.POSITION_COLOR);
				bufferBuilder.vertex((double)x, (double)(y + height), 0.0D).color(255, 255, 255, 75).next();
				bufferBuilder.vertex((double)(x + width), (double)(y + height), 0.0D).color(0, 0, 0, 0).next();
				bufferBuilder.vertex((double)(x + width), (double)y, 0.0D).color(0, 0, 0, 0).next();
				bufferBuilder.vertex((double)x, (double)y, 0.0D).color(255, 255, 255, 75).next();
				tessellator.draw();
				RenderSystem.enableTexture();
				RenderSystem.disableBlend();
			}
			this.mainButton.x = x;
			this.mainButton.y = y;
			this.toggleButton.x = x + width - this.toggleButton.getWidth() - 20;
			this.toggleButton.y = y + height/2 - 10;
			this.toggleButton.render(mouseX, mouseY, delta);
			TweakConfigListWidget.this.drawString(this.font, this.tweak.getLocalizedName(), 10, y + height/2 - 5, color);
		}

		@Override
		public List<? extends Element> children() {
			List<Element> children = new ArrayList<Element>();
			children.add(this.toggleButton);
			children.add(this.mainButton);
			return children;
		}

	}
}
