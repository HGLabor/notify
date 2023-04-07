package de.hglabor.notify.mixins.server;

import de.hglabor.notify.events.player.PlayerAttackEntityEvent;
import me.obsilabor.alert.EventManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.SERVER)
@Mixin(PlayerEntity.class)
public class MixinPlayerEntity {
    @Inject(method = "attack", at = @At("HEAD"), cancellable = true)
    public void attack(Entity entity, CallbackInfo ci) {
        var evt = EventManager.callEvent(new PlayerAttackEntityEvent((PlayerEntity) (Object) this, entity));
        if (evt.isCancelled()) ci.cancel();
    }
}
