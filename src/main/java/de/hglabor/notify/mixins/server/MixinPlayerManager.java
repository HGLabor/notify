package de.hglabor.notify.mixins.server;

import de.hglabor.notify.events.server.player.PlayerJoinEvent;
import de.hglabor.notify.events.server.player.PlayerRemoveEvent;
import de.hglabor.notify.events.server.player.PostPlayerJoinEvent;
import de.hglabor.notify.events.server.player.PrePlayerJoinEvent;
import me.obsilabor.alert.EventManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.SERVER)
@Mixin(value = PlayerManager.class)
public class MixinPlayerManager {

    @Unique private ServerPlayerEntity notify$tempPlayer = null;

    @Inject(method = "onPlayerConnect", at = @At("HEAD"))
    public void onPlayerConnectPre(ClientConnection clientConnection, ServerPlayerEntity player, CallbackInfo ci) {
        notify$tempPlayer = player;
        EventManager.callEvent(new PrePlayerJoinEvent(player));
    }

    @Inject(method = "onPlayerConnect", at = @At("TAIL"))
    public void onPlayerConnectPost(ClientConnection clientConnection, ServerPlayerEntity player, CallbackInfo ci) {
        if (player.isDisconnected()) return; // Don't call event if played got kicked
        EventManager.callEvent(new PostPlayerJoinEvent(player));
    }

    @Redirect(
        method = "onPlayerConnect",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/server/PlayerManager;broadcast(Lnet/minecraft/text/Text;Z)V"
        )
    )
    private void onPlayerConnect(PlayerManager instance, Text message, boolean overlay) {
        var evt = EventManager.callEvent(new PlayerJoinEvent(notify$tempPlayer, message));
        notify$tempPlayer = null;
        if (evt.getJoinMessage() != null) instance.broadcast(evt.getJoinMessage(), overlay);
    }

    @Inject(method = "remove", at = @At("TAIL"))
    public void remove(ServerPlayerEntity player, CallbackInfo ci) {
        EventManager.callEvent(new PlayerRemoveEvent(player));
    }
}
