package fr.smyler.mcct.gui.toasts;

import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.toast.Toast;
import net.minecraft.client.toast.ToastManager;

public class GenericToast implements Toast {
	
	private boolean justUpdated = true;
	private long startTime;
	protected String titleKey;
	protected String descriptionKey;
	
	public GenericToast(String titleKey, String descriptionKey) {
		this.titleKey = titleKey;
		this.descriptionKey = descriptionKey;
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
		manager.blit(0, 0, 0, 64, 160, 32);
		if (this.descriptionKey == null) {
			manager.getGame().textRenderer.draw(this.getLocalizedTitle(), 18.0F, 12.0F, -256);
		} else {
			manager.getGame().textRenderer.draw(this.getLocalizedTitle(), 18.0F, 7.0F, -256);
			manager.getGame().textRenderer.draw(this.getLocalizedDescription(), 18.0F, 18.0F, -1);
		}

		return currentTime - this.startTime < 5000L ? Toast.Visibility.SHOW : Toast.Visibility.HIDE;
	}
	
	public String getLocalizedDescription() {
		return I18n.translate(this.descriptionKey);
	}
	
	public String getLocalizedTitle() {
		return I18n.translate(this.titleKey);
	}

}
