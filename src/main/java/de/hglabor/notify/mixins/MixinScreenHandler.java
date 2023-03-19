package de.hglabor.notify.mixins;

import de.hglabor.notify.events.player.PlayerSlotClickEvent;
import me.obsilabor.alert.EventManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.SlotActionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ScreenHandler.class)
public class MixinScreenHandler {
    @Inject(method = "onSlotClick", at = @At("HEAD"), cancellable = true)
    public void onSlotClick(int slotId, int j, SlotActionType slotActionType, PlayerEntity playerEntity, CallbackInfo ci) {
        var evt = new PlayerSlotClickEvent(playerEntity, slotId, slotActionType);
        EventManager.callEvent(evt);
        if (evt.isCancelled()) {
            playerEntity.currentScreenHandler.syncState();
            ci.cancel();
        }
    }
}
