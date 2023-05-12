package de.hglabor.notify.mixins;

import de.hglabor.notify.events.entity.EntityDamageEvent;
import me.obsilabor.alert.EventManager;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class MixinLivingEntity {

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void onHurt(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        final var event = EventManager.callEvent(new EntityDamageEvent((LivingEntity) (Object) this, source, amount));
        if (event.isCancelled()) cir.setReturnValue(false);
    }
}