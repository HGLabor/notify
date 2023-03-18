package de.hglabor.notify.mixins.server;

import de.hglabor.notify.events.server.player.PlayerSwapHandItemsEvent;
import me.obsilabor.alert.EventManager;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayNetworkHandler.class)
public class MixinServerPlayNetworkHandler {
    @Shadow public ServerPlayerEntity player;

    @Inject(method = "onPlayerAction", at = @At(value = "INVOKE", target="Lnet/minecraft/server/network/ServerPlayerEntity;setStackInHand(Lnet/minecraft/util/Hand;Lnet/minecraft/item/ItemStack;)V"), cancellable = true)
    public void onPlayerAction(PlayerActionC2SPacket playerActionC2SPacket, CallbackInfo ci) {
        var evt = new PlayerSwapHandItemsEvent(player);
        EventManager.callEvent(evt);
        if (evt.isCancelled()) {
            // Sync client inventory
            player.currentScreenHandler.syncState();

            ci.cancel();
        }
    }
}
