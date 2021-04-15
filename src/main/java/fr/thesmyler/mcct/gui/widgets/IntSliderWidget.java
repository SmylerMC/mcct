package fr.thesmyler.mcct.gui.widgets;

import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.text.Text;

public abstract class IntSliderWidget extends SliderWidget {
	
	protected int min;
	protected int max;

	public IntSliderWidget(int x, int y, int width, int height, int value, int min, int max) {
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
	
	public int getVal() {
		return (int) Math.round(this.value * (max - min) + min);
	}
	
	public void setVal(int val) {
		double dval = (double)(val - min) / (max - min);
		this.value = dval;
		this.updateMessage();
	}
	
}
