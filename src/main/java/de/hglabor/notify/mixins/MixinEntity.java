package de.hglabor.notify.mixins;

import de.hglabor.notify.events.entity.EntityInitDataTrackerEvent;
import de.hglabor.notify.events.entity.EntityOnTrackedDataSetEvent;
import me.obsilabor.alert.EventManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.data.TrackedData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class MixinEntity {
    @Shadow
    protected abstract void initDataTracker();

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;initDataTracker()V"))
    private void initInjection(Entity instance) {
        var event = EventManager.callEvent(new EntityInitDataTrackerEvent(instance));
        if (!event.isCancelled()) {
            initDataTracker();
        }
    }

    @Inject(method = "onTrackedDataSet", at = @At("TAIL"))
    private void onTrackedDataSetInjection(TrackedData<?> data, CallbackInfo ci) {
        EventManager.callEvent(new EntityOnTrackedDataSetEvent((Entity) (Object) this, data));
    }
}
