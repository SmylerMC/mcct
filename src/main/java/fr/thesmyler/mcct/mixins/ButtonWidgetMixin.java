package fr.thesmyler.mcct.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import fr.thesmyler.mcct.gui.widgets.TweakedButtonWidget;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ButtonWidget.TooltipSupplier;

@Mixin(ButtonWidget.class)
public class ButtonWidgetMixin implements TweakedButtonWidget {

    @Shadow ButtonWidget.TooltipSupplier tooltipSupplier;

    @Override
    public TooltipSupplier getTooltipSupplier() {
        return this.tooltipSupplier;
    }
    
}
