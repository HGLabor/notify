package de.hglabor.notify.mixins;

import de.hglabor.notify.events.player.PlayerHungerChangeEvent;
import me.obsilabor.alert.EventManager;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HungerManager.class)
public class MixinHungerManager {
    @Shadow private int foodLevel;

    @Inject(method = "update", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/player/HungerManager;foodLevel:I", opcode = Opcodes.PUTFIELD), cancellable = true)
    private void onUpdate(PlayerEntity player, CallbackInfo ci) {
        var newValue = Math.max(foodLevel - 1, 0);
        if (newValue != foodLevel) {
            var evt = new PlayerHungerChangeEvent(player, foodLevel, newValue);
            EventManager.callEvent(evt);
            if (evt.isCancelled()) {
                ci.cancel();
            }
        }
    }
}
