package fr.smyler.mcct.gui.widgets;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import fr.smyler.mcct.config.ConfigBooleanValue;
import fr.smyler.mcct.config.ConfigFloatValue;
import fr.smyler.mcct.config.ConfigIntValue;
import fr.smyler.mcct.config.ConfigNullValue;
import fr.smyler.mcct.config.ConfigStringValue;
import fr.smyler.mcct.config.ConfigValue;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.widget.ElementListWidget;

public class ConfigValueListWidget extends ElementListWidget<ConfigValueListWidget.ValueEntry<?>> {

	public ConfigValueListWidget(MinecraftClient client, int width, int height, int top, int bottom, int itemHeight) {
		super(client, width, height, top, bottom, itemHeight);
	}

	public void addValueEntry(ConfigValue<?> configValue) {
		ValueEntry<?> entry;
		if(configValue instanceof ConfigIntValue) {
			entry = new IntValueEntry((ConfigIntValue)configValue);
		} else if(configValue instanceof ConfigFloatValue) {
			entry = new FloatValueEntry((ConfigFloatValue)configValue);
		} else if(configValue instanceof ConfigBooleanValue) {
			entry = new BooleanValueEntry((ConfigBooleanValue)configValue);
		} else if(configValue instanceof ConfigStringValue) {
			entry = new StringValueEntry((ConfigStringValue)configValue);
		} else {
			entry = new NullValueEntry((ConfigNullValue)configValue);
		}
		this.addEntry(entry);
	}

	public void addAll(ConfigValue<?>[] values) {
		for(int i = 0; i < values.length; i += 2) {
			this.addValueEntry(values[i]);
		}
	}

	public void addAll(Collection<ConfigValue<?>> values) {
		for(ConfigValue<?> tweak: values) {
			this.addValueEntry(tweak);
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

	public abstract class ValueEntry<T extends ConfigValue<?>> extends ElementListWidget.Entry<ConfigValueListWidget.ValueEntry<?>> {

		protected final T configValue;
		protected final TextRenderer font = MinecraftClient.getInstance().textRenderer;

		public ValueEntry(T configValue) {
			this.configValue = configValue;
		}


		@Override
		public List<? extends Element> children() {
			List<Element> children = new ArrayList<Element>();
			return children;
		}

	}

	public class IntValueEntry extends ValueEntry<ConfigIntValue> {

		//TODO Implement IntValueEntry

		public IntValueEntry(ConfigIntValue configValue) {
			super(configValue);
		}


		@Override
		public void render(int index, int y, int x, int width, int height, int mouseX, int mouseY, boolean hovering, float delta) {

		}

	}

	public class FloatValueEntry extends ValueEntry<ConfigFloatValue> {

		//TODO Implement FloatValueEntry

		public FloatValueEntry(ConfigFloatValue configValue) {
			super(configValue);
		}


		@Override
		public void render(int index, int y, int x, int width, int height, int mouseX, int mouseY, boolean hovering, float delta) {

		}

	}

	public class BooleanValueEntry extends ValueEntry<ConfigBooleanValue> {

		//TODO Implement BooleanValueEntry

		public BooleanValueEntry(ConfigBooleanValue configValue) {
			super(configValue);
		}


		@Override
		public void render(int index, int y, int x, int width, int height, int mouseX, int mouseY, boolean hovering, float delta) {

		}

	}

	public class StringValueEntry extends ValueEntry<ConfigStringValue> {

		//TODO Implement StringValueEntry

		public StringValueEntry(ConfigStringValue configValue) {
			super(configValue);
		}


		@Override
		public void render(int index, int y, int x, int width, int height, int mouseX, int mouseY, boolean hovering, float delta) {

		}

	}

	public class NullValueEntry extends ValueEntry<ConfigNullValue> {

		//TODO Implement StringNullEntry

		public NullValueEntry(ConfigNullValue configValue) {
			super(configValue);
		}


		@Override
		public void render(int index, int y, int x, int width, int height, int mouseX, int mouseY, boolean hovering, float delta) {

		}

	}
}
