package de.hglabor.notify.mixins;

import de.hglabor.notify.events.player.PlayerItemPickupEvent;
import me.obsilabor.alert.EventManager;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ItemEntity.class)
public class MixinItemEntity {
    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerInventory;insertStack(Lnet/minecraft/item/ItemStack;)Z"), method = "onPlayerCollision")
    public boolean onPlayerCollision(PlayerInventory playerInventory, ItemStack stack) {
        var player = playerInventory.player;
        var event = EventManager.callEvent(new PlayerItemPickupEvent(player, stack, (ItemEntity) (Object) this));

        if (event.isCancelled()) return false;
        return playerInventory.insertStack(stack);
    }
}
