package fr.thesmyler.mcct.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import fr.thesmyler.mcct.gui.widgets.TweakedButtonWidget;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ButtonWidget.PressAction;
import net.minecraft.client.gui.widget.ButtonWidget.TooltipSupplier;
import net.minecraft.client.gui.widget.PressableWidget;
import net.minecraft.text.Text;

@Mixin(ButtonWidget.class)
public abstract class ButtonWidgetMixin extends PressableWidget implements TweakedButtonWidget {
    
    public ButtonWidgetMixin(int i, int j, int k, int l, Text text) {
        super(i, j, k, l, text);
    }

    @Shadow ButtonWidget.TooltipSupplier tooltipSupplier;
    @Shadow ButtonWidget.PressAction onPress;

    @Override
    public TooltipSupplier getTooltipSupplier() {
        return this.tooltipSupplier;
    }

    @Override
    public PressAction getOnPressAction() {
        return this.onPress;
    }

    @Override
    public ButtonWidget withAction(PressAction action) {
        return new ButtonWidget(
                this.x, this.y,
                this.getWidth(), this.getHeight(),
                this.getMessage(),
                action,
                ((TweakedButtonWidget)this).getTooltipSupplier());
    }
    
}
