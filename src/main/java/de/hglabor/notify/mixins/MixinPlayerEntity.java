package de.hglabor.notify.mixins;

import de.hglabor.notify.events.player.PlayerAttackEntityEvent;
import de.hglabor.notify.events.player.PlayerNoClipEvent;
import de.hglabor.notify.events.player.PlayerGetActiveEyeHeightEvent;
import de.hglabor.notify.events.player.PlayerGetEntityDimensionsEvent;
import me.obsilabor.alert.EventManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.player.PlayerEntity;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(PlayerEntity.class)
public abstract class MixinPlayerEntity {
    @Inject(method = "attack", at = @At("HEAD"), cancellable = true)
    public void attack(Entity entity, CallbackInfo ci) {
        var evt = EventManager.callEvent(new PlayerAttackEntityEvent((PlayerEntity) (Object) this, entity));
        if (evt.isCancelled()) ci.cancel();
    }

    @Redirect(method = "tick", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/player/PlayerEntity;noClip:Z", opcode = Opcodes.PUTFIELD))
    private void noClipInjection(PlayerEntity instance, boolean value) {
        var event = EventManager.callEvent(new PlayerNoClipEvent(instance,value));
        instance.noClip = event.getNoClip();
    }

    @Inject(method = "getDimensions", at = @At("RETURN"), cancellable = true)
    private void getDimensionsInjection(EntityPose entityPose, CallbackInfoReturnable<EntityDimensions> cir) {
        var event = EventManager.callEvent(new PlayerGetEntityDimensionsEvent((PlayerEntity) ((Object) this), cir.getReturnValue()));
        cir.setReturnValue(event.getDimensions());
    }

    @Inject(method = "getActiveEyeHeight", at = @At("RETURN"), cancellable = true)
    private void getActiveEyeHeightInjection(EntityPose entityPose, EntityDimensions entityDimensions, CallbackInfoReturnable<Float> cir) {
        var event = EventManager.callEvent(new PlayerGetActiveEyeHeightEvent((PlayerEntity) ((Object) this), entityDimensions, entityPose, cir.getReturnValue()));
        cir.setReturnValue(event.getEyeHeight());
    }
}
