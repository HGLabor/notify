package de.hglabor.notify.mixins.client;

import de.hglabor.notify.events.client.MouseScrollEvent;
import me.obsilabor.alert.EventManager;
import net.minecraft.client.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mouse.class)
public abstract class MixinMouse {
    @Shadow
    private double eventDeltaWheel;

    @Inject(method = "onMouseScroll", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;isSpectator()Z"), cancellable = true)
    private void onMouseScrollInjection(long l, double d, double e, CallbackInfo ci) {
        var event = EventManager.callEvent(new MouseScrollEvent(this.eventDeltaWheel));
        if (event.isCancelled()) {
            //TODO not tested
            ci.cancel();
        }
    }
}
