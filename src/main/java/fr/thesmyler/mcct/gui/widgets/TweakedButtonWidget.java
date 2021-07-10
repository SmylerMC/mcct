package fr.thesmyler.mcct.gui.widgets;

import net.minecraft.client.gui.widget.ButtonWidget;

/**
 * Tweaked version of {@link ButtonWidget} with getters for hidden fields
 * 
 * @author SmylerMC
 *
 */
public interface TweakedButtonWidget {
    
    ButtonWidget.TooltipSupplier getTooltipSupplier();
    
    ButtonWidget.PressAction getOnPressAction();
    
    /**
     * Creates a copy of the current button but with a different press action
     * 
     * @param action - a new action for the copy
     * @return a copy of the button, with a different press action
     */
    ButtonWidget withAction(ButtonWidget.PressAction action);


}
