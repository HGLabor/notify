package de.hglabor.notify.mixins.client;

import de.hglabor.notify.events.client.GameJoinEvent;
import me.obsilabor.alert.EventManager;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.GameJoinS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class MixinClientPlayNetworkHandler {
    @Inject(method = "onGameJoin", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/packet/s2c/play/GameJoinS2CPacket;comp_1727()Lnet/minecraft/network/packet/s2c/play/CommonPlayerSpawnInfo;"))
    private void triggerJoinEvent(GameJoinS2CPacket gameJoinS2CPacket, CallbackInfo ci) {
        EventManager.callEvent(new GameJoinEvent());
    }
}