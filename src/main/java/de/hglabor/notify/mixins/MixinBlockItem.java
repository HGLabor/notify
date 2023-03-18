package de.hglabor.notify.mixins;

import de.hglabor.notify.events.player.PlayerPlaceBlockEvent;
import me.obsilabor.alert.EventManager;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockItem.class)
public class MixinBlockItem {

    @Inject(method = "place(Lnet/minecraft/item/ItemPlacementContext;)Lnet/minecraft/util/ActionResult;", at = @At("HEAD"), cancellable = true)
    public void place(ItemPlacementContext context, CallbackInfoReturnable<ActionResult> cir) {
        var player = context.getPlayer();
        if (player != null) {
            var evt = new PlayerPlaceBlockEvent(player, context);
            EventManager.callEvent(evt);
            if (evt.isCancelled()) {
                // Sync client inventory
                player.currentScreenHandler.syncState();
                cir.setReturnValue(ActionResult.FAIL);
            }
        }
    }
}
