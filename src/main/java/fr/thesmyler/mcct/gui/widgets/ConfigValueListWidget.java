package fr.thesmyler.mcct.gui.widgets;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mojang.blaze3d.systems.RenderSystem;

import fr.thesmyler.mcct.MCCT;
import fr.thesmyler.mcct.config.ConfigBooleanValue;
import fr.thesmyler.mcct.config.ConfigFloatValue;
import fr.thesmyler.mcct.config.ConfigIntValue;
import fr.thesmyler.mcct.config.ConfigNullValue;
import fr.thesmyler.mcct.config.ConfigStringValue;
import fr.thesmyler.mcct.config.ConfigValue;
import fr.thesmyler.mcct.config.InvalidConfigValue;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.widget.ElementListWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.ToggleButtonWidget;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ConfigValueListWidget extends ElementListWidget<ConfigValueListWidget.ValueEntry<?>> {

	public ConfigValueListWidget(MinecraftClient client, int width, int height, int top, int bottom, int itemHeight) {
		super(client, width, height, top, bottom, itemHeight);
	}

	public void addValueEntry(String key, ConfigValue<?> configValue) {
		ValueEntry<?> entry;
		if(configValue instanceof ConfigIntValue) {
			entry = new IntValueEntry(key, (ConfigIntValue)configValue);
		} else if(configValue instanceof ConfigFloatValue) {
			entry = new FloatValueEntry(key, (ConfigFloatValue)configValue);
		} else if(configValue instanceof ConfigBooleanValue) {
			entry = new BooleanValueEntry(key, (ConfigBooleanValue)configValue);
		} else if(configValue instanceof ConfigStringValue) {
			entry = new StringValueEntry(key, (ConfigStringValue)configValue);
		} else {
			entry = new NullValueEntry(key, (ConfigNullValue)configValue);
		}
		this.addEntry(entry);
	}

	public void addAll(Map<String, ConfigValue<?>> values) {
		for(String key: values.keySet()) {
			this.addValueEntry(key, values.get(key));
		}
	}
	
	public void clear() {
		this.clearEntries();
	}

	@Override
	public int getRowWidth() {
		return this.width;
	}

	@Override
	protected int getScrollbarPositionX() {
		return this.left + this.width - 5;
	}
	
	public void updateFromTweaks() {
		for(Element element: this.children()) {
			if(element instanceof ValueEntry<?>) {
				ValueEntry<?> entry = (ValueEntry<?>)element;
				entry.updateFromValue();
			}
		}
		
	}

	public abstract class ValueEntry<T extends ConfigValue<?>> extends ElementListWidget.Entry<ConfigValueListWidget.ValueEntry<?>> {

		protected String key;
		protected final T configValue;
		protected final TextRenderer font = MinecraftClient.getInstance().textRenderer;

		public ValueEntry(String key, T configValue) {
			this.key = key;
			this.configValue = configValue;
		}
		
		public void renderBackground(int index, int y, int x, int width, int height, int mouseX, int mouseY, boolean hovering, float delta) {
			Tessellator tessellator = Tessellator.getInstance();
			BufferBuilder bufferBuilder = tessellator.getBuffer();
			RenderSystem.enableBlend();
			RenderSystem.shadeModel(7425);
			RenderSystem.disableTexture();
			bufferBuilder.begin(7, VertexFormats.POSITION_COLOR);
			int c = index % 2 + 1;
			int cb = hovering ? 220 : 255;
			int baseAlpha = 30;
			bufferBuilder.vertex((double)x, (double)(y + height), 0.0D).color(cb, cb, 255, baseAlpha*c).next();
			bufferBuilder.vertex((double)(x + width - 10), (double)(y + height), 0.0D).color(cb, cb, 255, baseAlpha*c).next();
			bufferBuilder.vertex((double)(x + width - 10), (double)y, 0.0D).color(cb, cb, 255, baseAlpha*c).next();
			bufferBuilder.vertex((double)x, (double)y, 0.0D).color(cb, cb, 255, baseAlpha*c).next();
			tessellator.draw();
			RenderSystem.enableTexture();
			RenderSystem.disableBlend();
		}
		
		public String getLocalizedKey() {
			return I18n.translate(MCCT.MOD_ID + ".configvalues." + this.key);
		}
		
		public void resetValue() {
			this.configValue.reset();
			this.updateFromValue();
		}
		
		public abstract void updateFromValue() ;

	}

	public class IntValueEntry extends ValueEntry<ConfigIntValue> {

		private IntSliderWidget slider = null;
		private TextFieldWidget textField;

		public IntValueEntry(String key, ConfigIntValue configValue) {
			super(key, configValue);
			if(this.configValue.getMin() != null && this.configValue.getMax() != null) {
				this.slider = new IntSliderWidget(0, 0, 0, 20, this.configValue.get(), this.configValue.getMin(), this.configValue.getMax()) {

					@Override
					protected void applyValue() {
						IntValueEntry entry = IntValueEntry.this;
						entry.configValue.set(this.getVal());
						entry.textField.setText("" + this.getVal());
						entry.textField.setCursorToStart();
					}
					
				};

					
			}
			this.textField = new TextFieldWidget(font, 0, 0, 50, 20, Text.of(""));
			this.textField.setChangedListener((text) -> {
				try {
					int val = Integer.parseInt(text);
					this.configValue.set(val);
					this.slider.setVal(val);
					this.textField.setEditableColor(0xFFFFFF);
				} catch(InvalidConfigValue | NumberFormatException e) {
					this.textField.setEditableColor(0xFF0000);
				}
			});
			this.textField.setText("" + this.slider.getVal());
			this.textField.setCursorToStart();
		}


		@Override
		public void render(MatrixStack matrices, int index, int y, int x, int width, int height, int mouseX, int mouseY, boolean hovering, float delta) {
			this.renderBackground(index, y, x, width, height, mouseX, mouseY, hovering, delta);
			if(this.slider != null) {
				this.slider.setWidth(Math.min(width - 20, 390));
				this.slider.y = y + 26;
				this.slider.x = x + (x - slider.getWidth())/2 - 12;
				this.slider.render(matrices, mouseX, mouseY, delta);
			}
			this.textField.x = x + width - this.textField.getWidth() - 16;
			this.textField.y = y + 3;
			this.textField.render(matrices, mouseX, mouseY, delta);
			DrawableHelper.drawStringWithShadow(matrices, this.font, this.getLocalizedKey(), x+5, y+9, 0xFFFFFF);
		}


		@Override
		public List<? extends Element> children() {
			ArrayList<Element> children = new ArrayList<Element>();
			children.add(this.textField);
			if(this.slider != null) children.add(this.slider);
			return children;
		}

		@Override
		public void updateFromValue() {
			this.slider.setVal(this.configValue.get());
			this.textField.setText("" + this.slider.getVal());
			this.textField.setCursorToStart();
		}

	}

	public class FloatValueEntry extends ValueEntry<ConfigFloatValue> {

		private FloatSliderWidget slider = null;
		private TextFieldWidget textField;

		public FloatValueEntry(String key, ConfigFloatValue configValue) {
			super(key, configValue);
			if(this.configValue.getMin() != null && this.configValue.getMax() != null) {
				this.slider = new FloatSliderWidget(0, 0, 0, 20, this.configValue.get(), this.configValue.getMin(), this.configValue.getMax()) {

					@Override
					protected void applyValue() {
						FloatValueEntry entry = FloatValueEntry.this;
						entry.configValue.set(this.getVal());
						entry.textField.setText("" + this.getVal());
						entry.textField.setCursorToStart();
					}
					
				};
			}
			this.textField = new TextFieldWidget(font, 0, 0, 50, 20, Text.of(""));
			this.textField.setChangedListener((text) -> {
				try {
					float val = Float.parseFloat(text);
					this.configValue.set(val);
					this.slider.setVal(val);
					this.textField.setEditableColor(0xFFFFFF);
				} catch(InvalidConfigValue | NumberFormatException e) {
					this.textField.setEditableColor(0xFF0000);
				}
			});
			this.textField.setText("" + this.slider.getVal());
			this.textField.setCursorToStart();
		}


		@Override
		public void render(MatrixStack matrices, int index, int y, int x, int width, int height, int mouseX, int mouseY, boolean hovering, float delta) {
			this.renderBackground(index, y, x, width, height, mouseX, mouseY, hovering, delta);
			if(this.slider != null) {
				this.slider.setWidth(Math.min(width - 20, 390));
				this.slider.y = y + 26;
				this.slider.x = x + (x - slider.getWidth())/2 - 12;
				this.slider.render(matrices, mouseX, mouseY, delta);
			}
			this.textField.x = x + width - this.textField.getWidth() - 16;
			this.textField.y = y + 3;
			this.textField.render(matrices, mouseX, mouseY, delta);
			DrawableHelper.drawStringWithShadow(matrices, this.font, this.getLocalizedKey(), x+5, y+9, 0xFFFFFF);
		}

		@Override
		public List<? extends Element> children() {
			ArrayList<Element> children = new ArrayList<Element>();
			children.add(textField);
			if(this.slider != null) children.add(this.slider);
			return children;
		}


		@Override
		public void updateFromValue() {
			this.slider.setVal(this.configValue.get());
			this.textField.setText("" + this.slider.getVal());
			this.textField.setCursorToStart();
		}

	}

	public class BooleanValueEntry extends ValueEntry<ConfigBooleanValue> {

		private ToggleButtonWidget toggleButton;

		public BooleanValueEntry(String key, ConfigBooleanValue configValue) {
			super(key, configValue);
			this.toggleButton = new ToggleButtonWidget(0, 0, 26, 16, this.configValue.get()){

				@Override
				public void onClick(double mouseX, double mouseY) {
					if(this.active) {
						this.toggled = !this.toggled;
						BooleanValueEntry.this.configValue.set(this.isToggled());
					}
				}

			};
			this.toggleButton.setTextureUV(2, 2, 28, 18, new Identifier(MCCT.MOD_ID, "textures/gui/widgets.png"));
		}

		@Override
		public void render(MatrixStack matrices, int index, int y, int x, int width, int height, int mouseX, int mouseY, boolean hovering, float delta) {
			this.renderBackground(index, y, x, width, height, mouseX, mouseY, hovering, delta);
			this.toggleButton.x = ConfigValueListWidget.this.left + width - this.toggleButton.getWidth() - 15;
			this.toggleButton.y = y + height / 2 - 7;
			this.toggleButton.render(matrices, mouseX, mouseY, delta);
			DrawableHelper.drawStringWithShadow(matrices, this.font, this.getLocalizedKey(), x+10, y + height/2 - 2, 0xFFFFFF);
		}


		@Override
		public List<? extends Element> children() {
			ArrayList<Element> children = new ArrayList<Element>();
			children.add(this.toggleButton);
			return children;
		}

		@Override
		public void updateFromValue() {
			this.toggleButton.setToggled(this.configValue.get());
		}

	}

	public class StringValueEntry extends ValueEntry<ConfigStringValue> {

		private TextFieldWidget textField;

		public StringValueEntry(String key, ConfigStringValue configValue) {
			super(key, configValue);
			this.textField = new TextFieldWidget(this.font, 0, 0, 0, 20, Text.of(this.configValue.get()));
			this.textField.setText(this.configValue.get());
			this.textField.setChangedListener((text) -> {
				try {
					this.configValue.set(text);
					this.textField.setEditableColor(0xFFFFFF);
				} catch(InvalidConfigValue e) {
					this.textField.setEditableColor(0xFF0000);
				}
			});
			this.textField.setCursorToStart();
		}


		@Override
		public void render(MatrixStack matrices, int index, int y, int x, int width, int height, int mouseX, int mouseY, boolean hovering, float delta) {
			this.renderBackground(index, y, x, width, height, mouseX, mouseY, hovering, delta);
			this.textField.x = x + 5;
			this.textField.y = y + 25;
			this.textField.setWidth(width - 20);
			this.textField.render(matrices, mouseX, mouseY, delta);
			DrawableHelper.drawCenteredString(matrices, this.font, this.getLocalizedKey(), x + width/2 -5, y + 8, 0xFFFFFF);
		}


		@Override
		public List<? extends Element> children() {
			ArrayList<Element> children = new ArrayList<Element>();
			children.add(this.textField);
			return children;
		}


		@Override
		public void updateFromValue() {
			this.textField.setText(this.configValue.get());
			this.textField.setCursorToStart();
		}

	}

	public class NullValueEntry extends ValueEntry<ConfigNullValue> {

		public NullValueEntry(String key, ConfigNullValue configValue) {
			super(key, configValue);
		}


		@Override
		public void render(MatrixStack matrices, int index, int y, int x, int width, int height, int mouseX, int mouseY, boolean hovering, float delta) {
			this.renderBackground(index, y, x, width, height, mouseX, mouseY, hovering, delta);
			DrawableHelper.drawStringWithShadow(matrices, this.font, this.getLocalizedKey(), x, y, 0xFFFFFF);
		}


		@Override
		public List<? extends Element> children() {
			return new ArrayList<Element>();
		}


		@Override
		public void updateFromValue() {}

	}
}
