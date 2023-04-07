package de.hglabor.notify.mixins.server;

import de.hglabor.notify.events.server.player.PlayerBreakBlockEvent;
import de.hglabor.notify.events.server.player.PlayerInteractItemEvent;
import me.obsilabor.alert.EventManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.SERVER)
@Mixin(value = ServerPlayerInteractionManager.class)
public class MixinServerPlayerInteractionManager {
    @Shadow @Final protected ServerPlayerEntity player;

    @Inject(method = "interactItem", at = @At("HEAD"), cancellable = true)
    public void interactItem(ServerPlayerEntity player, World world, ItemStack stack, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        var evt = new PlayerInteractItemEvent(player, stack, hand);
        EventManager.callEvent(evt);

        if (evt.isCancelled()) {
            cir.setReturnValue(ActionResult.CONSUME);
        }
    }

    @Inject(method = "tryBreakBlock", at = @At("HEAD"), cancellable = true)
    public void tryBreakBlock(BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        var evt = new PlayerBreakBlockEvent(player, pos);
        EventManager.callEvent(evt);
        if (evt.isCancelled()) cir.setReturnValue(false);
    }
}
