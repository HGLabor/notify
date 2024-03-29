package de.hglabor.notify.mixins.server;

import de.hglabor.notify.events.server.player.PlayerInteractBlockEvent;
import de.hglabor.notify.events.server.player.PlayerQuitEvent;
import de.hglabor.notify.events.server.player.PlayerSwapHandItemsEvent;
import me.obsilabor.alert.EventManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerInteractBlockC2SPacket;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.SERVER)
@Mixin(ServerPlayNetworkHandler.class)
public class MixinServerPlayNetworkHandler {
    @Shadow public ServerPlayerEntity player;

    @Inject(method = "onPlayerAction", at = @At(value = "INVOKE", target="Lnet/minecraft/server/network/ServerPlayerEntity;setStackInHand(Lnet/minecraft/util/Hand;Lnet/minecraft/item/ItemStack;)V"), cancellable = true)
    public void onPlayerAction(PlayerActionC2SPacket playerActionC2SPacket, CallbackInfo ci) {
        var evt = EventManager.callEvent(new PlayerSwapHandItemsEvent(player));
        if (evt.isCancelled()) {
            // Sync client inventory
            player.currentScreenHandler.syncState();

            ci.cancel();
        }
    }

    @Redirect(
        method = "cleanUp",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/server/PlayerManager;broadcast(Lnet/minecraft/text/Text;Z)V"
        )
    )
    private void onPlayerDisconnect(PlayerManager instance, Text message, boolean overlay) {
        var evt = EventManager.callEvent(new PlayerQuitEvent(player, message));
        if (evt.getQuitMessage() != null) instance.broadcast(evt.getQuitMessage(), overlay);
    }

    @Inject(method = "onPlayerInteractBlock", at = @At("HEAD"), cancellable = true)
    public void onPlayerInteractBlock(PlayerInteractBlockC2SPacket packet, CallbackInfo ci) {
        var evt = EventManager.callEvent(new PlayerInteractBlockEvent(player, packet.getHand(), packet.getBlockHitResult()));
        if (evt.isCancelled()) {
            // Sync client inventory
            player.currentScreenHandler.syncState();
            ci.cancel();
        }
    }
}
