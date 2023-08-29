package de.hglabor.notify.mixins.client;

import de.hglabor.notify.events.client.GameDisconnectEvent;
import me.obsilabor.alert.EventManager;
import net.minecraft.network.ClientConnection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientConnection.class)
public class MixinClientConnection {
    @Inject(method = "handleDisconnection", at = @At("HEAD"))
    private void onDisconnection(CallbackInfo ci) {
        EventManager.callEvent(new GameDisconnectEvent());
    }
}
