package de.hglabor.notify.mixins.client;

import de.hglabor.notify.events.client.PumpkinOverlayRenderEvent;
import me.obsilabor.alert.EventManager;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(InGameHud.class)
public class MixinInGameHud {
    @Inject(at = @At("HEAD"), method = "renderOverlay", cancellable = true)
    private void onRenderPumpkinBlur(DrawContext context, Identifier texture, float opacity, CallbackInfo ci) {
        if (!Objects.equals(texture, new Identifier("textures/misc/pumpkinblur.png"))) return;
        final var event = EventManager.callEvent(new PumpkinOverlayRenderEvent());
        if (event.isCancelled()) ci.cancel();
    }
}
