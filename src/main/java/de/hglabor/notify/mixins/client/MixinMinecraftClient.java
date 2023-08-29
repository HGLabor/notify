package de.hglabor.notify.mixins.client;

import de.hglabor.notify.events.client.ClientStopEvent;
import de.hglabor.notify.events.client.PostTickEvent;
import de.hglabor.notify.events.client.PreTickEvent;
import me.obsilabor.alert.EventManager;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public abstract class MixinMinecraftClient {

    @Inject(method = "stop", at = @At("HEAD"))
    private void stop(CallbackInfo callback) {
        EventManager.callEvent(new ClientStopEvent());
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void preTick(CallbackInfo ci) {
        EventManager.callEvent(new PreTickEvent());
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void postTick(CallbackInfo ci) {
        EventManager.callEvent(new PostTickEvent());
    }
}
