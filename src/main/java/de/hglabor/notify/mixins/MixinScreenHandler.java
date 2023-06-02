package de.hglabor.notify.mixins;

import de.hglabor.notify.events.player.PlayerSlotClickEvent;
import de.hglabor.notify.events.server.player.PlayerCraftItemEvent;
import me.obsilabor.alert.EventManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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
        ItemStack stack = null;
        try {
             stack = playerEntity.currentScreenHandler.getSlot(slotId).getStack();
        }
        catch (Exception ignored) {}
        // click event
        var evt = EventManager.callEvent(new PlayerSlotClickEvent(playerEntity, stack, slotId, slotActionType));
        if (evt.isCancelled()) {
            playerEntity.currentScreenHandler.syncState();
            ci.cancel();
        }
        else if (stack != null && stack.getItem() != Items.AIR && slotId == 0) {
            // craft event
            var craftEvent = EventManager.callEvent(new PlayerCraftItemEvent(playerEntity, stack, slotActionType));
            if (craftEvent.isCancelled()) {
                playerEntity.currentScreenHandler.syncState();
                ci.cancel();
            }
        }
    }
}
