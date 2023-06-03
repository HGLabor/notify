package de.hglabor.notify.mixins.server;

import de.hglabor.notify.events.server.entity.EntitySpawnEvent;
import me.obsilabor.alert.EventManager;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerEntityManager;
import net.minecraft.world.entity.EntityLike;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerEntityManager.class)
public class MixinServerEntityManager<T extends EntityLike> {
    @Inject(method = "addEntity(Lnet/minecraft/world/entity/EntityLike;Z)Z", at = @At("HEAD"), cancellable = true)
    public void addEntity(T entityLike, boolean existing, CallbackInfoReturnable<Boolean> cir) {
        if (entityLike instanceof Entity entity) {
            var evt = EventManager.callEvent(new EntitySpawnEvent(entity, existing));
            if (evt.isCancelled()) {
                cir.setReturnValue(false);
            }
        }
    }
}
