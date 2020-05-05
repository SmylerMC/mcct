package fr.smyler.mcct.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import fr.smyler.mcct.gui.screens.recipebook.TweakedRecipeBookResults;
import net.minecraft.client.gui.screen.recipebook.RecipeBookResults;


@Mixin(value=RecipeBookResults.class)
public abstract class RecipeBookResultsMixin implements TweakedRecipeBookResults{

	@Shadow private int pageCount;
	@Shadow private int currentPage;

	@Shadow
	public abstract void refreshResultButtons();

	@Override
	public void nextPageIfPossible() {
		if(this.currentPage < this.pageCount - 1) {
			++this.currentPage;
			this.refreshResultButtons();
		}
	}

	@Override
	public void prevPageIfPossible() {
		if(this.currentPage > 0) {
			--this.currentPage;
			this.refreshResultButtons();
		}
	}
}
