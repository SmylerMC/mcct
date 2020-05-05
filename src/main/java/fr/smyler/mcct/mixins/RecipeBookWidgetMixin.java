package fr.smyler.mcct.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import fr.smyler.mcct.gui.screens.recipebook.TweakedRecipeBookResults;
import fr.smyler.mcct.tweaks.Tweaks;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.recipebook.RecipeBookResults;
import net.minecraft.client.gui.screen.recipebook.RecipeBookWidget;
import net.minecraft.client.gui.screen.recipebook.RecipeDisplayListener;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeGridAligner;

@Mixin(value=RecipeBookWidget.class)
public abstract class RecipeBookWidgetMixin extends DrawableHelper implements Drawable, Element, RecipeDisplayListener, RecipeGridAligner<Ingredient>{

	@Shadow private int leftOffset;
	@Shadow private int parentWidth;
	@Shadow private int parentHeight;
	@Shadow protected final RecipeBookResults recipesArea = new RecipeBookResults();

	@Override
	public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
		if(!Tweaks.INVENTORY.isActivated() || !Tweaks.INVENTORY.MOUSE_WHEEL_IN_RECIPE_BOOK.get()) return false;
		TweakedRecipeBookResults recipeBook = (TweakedRecipeBookResults) this.recipesArea;
		double direction = amount;
		if(Tweaks.INVENTORY.INVERTED_MOUSE_WHEEL.get()) direction *= -1;
		if(direction < 0) recipeBook.nextPageIfPossible();
		else if(direction > 0) recipeBook.prevPageIfPossible();
		return false; //TODO true?
	}

	@Inject(at=@At("HEAD"), method="isMouseOver(DD)Z", cancellable=true)
	public void isMouseOverTweak(double mouseX, double mouseY, CallbackInfoReturnable<Boolean> info) {
		//For some reason the vanilla behavior is to always return false, which causes the parent screen to never call mouseScrolled
		if(!Tweaks.INVENTORY.isActivated() || !Tweaks.INVENTORY.MOUSE_WHEEL_IN_RECIPE_BOOK.get()) return;
		int x = (this.parentWidth - 147) / 2 - this.leftOffset;
		int y = (this.parentHeight - 166) / 2;
		boolean returned =  mouseX >= x && mouseY >= y && mouseX <= x + 147 && mouseY <= y + 166;
		info.setReturnValue(returned);
	}
}
