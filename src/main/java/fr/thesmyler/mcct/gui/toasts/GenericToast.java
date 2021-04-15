package fr.thesmyler.mcct.gui.toasts;

import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.toast.Toast;
import net.minecraft.client.toast.ToastManager;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public class GenericToast implements Toast {
	
	private boolean justUpdated = true;
	private long startTime;
	protected String titleKey;
	protected String descriptionKey;
	protected ItemStack icon;
	
	public GenericToast(String titleKey, String descriptionKey, ItemStack icon) {
		this.titleKey = titleKey;
		this.descriptionKey = descriptionKey;
		this.icon = icon;
	}
		

	@SuppressWarnings("resource")
	@Override
	public Visibility draw(ToastManager manager, long currentTime) {
		if (this.justUpdated) {
			this.startTime = currentTime;
			this.justUpdated = false;
		}

		manager.getGame().getTextureManager().bindTexture(TOASTS_TEX);
		RenderSystem.color3f(1.0F, 1.0F, 1.0F);
		manager.blit(0, 0, 0, 32, 160, 32);
		float xpos = this.icon != null ? 30: 10;
		if (this.descriptionKey == null) {
			manager.getGame().textRenderer.draw(this.getLocalizedTitle(), xpos, 12, 0x000000);
		} else {
			manager.getGame().textRenderer.draw(this.getLocalizedTitle(), xpos, 7, 0x000000);
			manager.getGame().textRenderer.draw(this.getLocalizedDescription(), xpos, 18, 0xFF2222);
		}
		if(this.icon != null) manager.getGame().getItemRenderer().renderGuiItem((LivingEntity)null, this.icon, 8, 8);
		return currentTime - this.startTime < 5000L ? Toast.Visibility.SHOW : Toast.Visibility.HIDE;
	}
	
	public String getLocalizedDescription() {
		return I18n.translate(this.descriptionKey);
	}
	
	public String getLocalizedTitle() {
		return I18n.translate(this.titleKey);
	}

}
