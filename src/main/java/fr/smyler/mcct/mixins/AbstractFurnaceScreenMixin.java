package fr.smyler.mcct.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.gui.screen.ingame.AbstractFurnaceScreen;
import net.minecraft.client.gui.screen.ingame.ContainerScreen;
import net.minecraft.client.gui.screen.recipebook.AbstractFurnaceRecipeBookScreen;
import net.minecraft.client.gui.screen.recipebook.RecipeBookProvider;
import net.minecraft.container.AbstractFurnaceContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;

@Mixin(value=AbstractFurnaceScreen.class)
public abstract class AbstractFurnaceScreenMixin<T extends AbstractFurnaceContainer> extends ContainerScreen<T> implements RecipeBookProvider{

	@Shadow public AbstractFurnaceRecipeBookScreen recipeBook;
	
	public AbstractFurnaceScreenMixin(T container, PlayerInventory playerInventory, Text name) {
		super(container, playerInventory, name);
	}
	
	@Inject(at=@At("TAIL"), method="init()V")
	public void initTweak(CallbackInfo info) {
		this.children.add(this.recipeBook);
	}

}
