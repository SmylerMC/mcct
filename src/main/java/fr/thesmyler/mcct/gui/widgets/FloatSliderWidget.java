package fr.thesmyler.mcct.gui.widgets;

import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.text.Text;

public abstract class FloatSliderWidget extends SliderWidget {
	
	protected float min;
	protected float max;

	public FloatSliderWidget(int x, int y, int width, int height, float value, float min, float max) {
		super(x, y, width, height, Text.of(""), 0);
		this.min = min;
		this.max = max;
		this.setVal(value);
		this.updateMessage();
	}

	@Override
	protected void updateMessage() {
		this.setMessage(Text.of("" + this.getVal()));
	}
	
	public float getVal() {
		return (float) ((double)Math.round((this.value * (max - min) + min)*100f) / 100f);
	}
	
	public void setVal(float val) {
		double dval = (double)(val - min) / (max - min);
		this.value = dval;
		this.updateMessage();
	}
	
}
