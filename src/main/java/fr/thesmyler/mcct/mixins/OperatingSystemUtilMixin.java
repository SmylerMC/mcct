package fr.thesmyler.mcct.mixins;

import java.lang.ProcessBuilder.Redirect;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import fr.thesmyler.mcct.MCCT;
import fr.thesmyler.mcct.tweaks.Tweaks;
import net.minecraft.util.Util;

@Mixin(Util.OperatingSystem.class)
public abstract class OperatingSystemUtilMixin {
	
	@Shadow protected abstract String[] getURLOpenCommand(URL url);
	
	@Inject(at=@At("HEAD"), method="open(Ljava/net/URL;)V", cancellable=true)
	public void open(URL url, CallbackInfo info) {
		if(!Tweaks.BUGFIXES.LINUX_OPENFILE.get()) return;
		try {
            AccessController.doPrivileged((PrivilegedExceptionAction<Process>)() -> {
               return new ProcessBuilder().command(this.getURLOpenCommand(url)).redirectError(Redirect.INHERIT).start();
            });
         } catch (PrivilegedActionException var5) {
            MCCT.LOGGER.error((String)"Couldn't open url '{}'", (Object)url, (Object)var5);
         }
		info.cancel();
     }

}
