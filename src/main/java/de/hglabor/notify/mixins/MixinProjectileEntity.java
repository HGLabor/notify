package de.hglabor.notify.mixins;

import de.hglabor.notify.events.server.entity.ProjectileHitEvent;
import me.obsilabor.alert.EventManager;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ProjectileEntity.class)
public class MixinProjectileEntity {
    @Inject(method = "onCollision", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/projectile/ProjectileEntity;onEntityHit(Lnet/minecraft/util/hit/EntityHitResult;)V"))
    public void onEntityCollision(HitResult hitResult, CallbackInfo ci) {
        EventManager.callEvent(new ProjectileHitEvent((ProjectileEntity) (Object) this, (EntityHitResult) hitResult));
    }
}
