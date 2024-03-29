package de.hglabor.notify.mixins.server;

import de.hglabor.notify.events.server.player.*;
import me.obsilabor.alert.EventManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.c2s.common.SyncedClientOptions;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Environment(EnvType.SERVER)
@Mixin(value = ServerPlayerEntity.class)
public abstract class MixinServerPlayerEntity {
    @Inject(method = "tick", at = @At("TAIL"))
    public void tick(CallbackInfo ci) {
        EventManager.callEvent(new PlayerTickEvent((ServerPlayerEntity) (Object) this));
    }

    @Inject(method = "dropSelectedItem", at = @At("HEAD"), cancellable = true)
    public void dropSelectedItem(boolean dropAll, CallbackInfoReturnable<Boolean> cir) {
        var player = ((ServerPlayerEntity) (Object) this);
        var evt = EventManager.callEvent(new PlayerItemDropEvent(player, player.getMainHandStack()));
        if (evt.isCancelled()) {
            // Sync client inventory
            player.currentScreenHandler.syncState();
            cir.setReturnValue(false);
        }
    }

    /**
     * Called when item was already dropped; call PlayerItem<em>Dropped</em>Event
     */
    @Inject(method = "dropItem", at = @At("TAIL"), locals = LocalCapture.CAPTURE_FAILHARD)
    public void injectDropItem(ItemStack stack, boolean throwRandomly, boolean retainOwnership, CallbackInfoReturnable<ItemEntity> cir, ItemEntity itemEntity) {
        var player = ((ServerPlayerEntity) (Object) this);
        EventManager.callEvent(new PlayerItemDroppedEvent(player, itemEntity));
    }

    @Inject(method = "onDeath", at = @At("HEAD"), cancellable = true)
    public void onDeath(CallbackInfo ci) {
        var player = ((ServerPlayerEntity) (Object) this);
        var evt = EventManager.callEvent(new PlayerDeathEvent(player));
        if (evt.isCancelled()) {
            ci.cancel();
        }
    }

    @Inject(method = "setClientOptions", at = @At("HEAD"))
    public void setClientOptions(SyncedClientOptions clientOptions, CallbackInfo ci) {
        var player = ((ServerPlayerEntity) (Object) this);
        EventManager.callEvent(new PlayerSetClientOptionEvent(player, clientOptions));
    }
}
