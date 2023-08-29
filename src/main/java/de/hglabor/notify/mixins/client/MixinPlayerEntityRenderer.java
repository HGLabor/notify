package de.hglabor.notify.mixins.client;

import de.hglabor.notify.events.client.PlayerEntityRendererScaleEvent;
import me.obsilabor.alert.EventManager;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(PlayerEntityRenderer.class)
public abstract class MixinPlayerEntityRenderer {
    @ModifyArgs(method = "scale(Lnet/minecraft/client/network/AbstractClientPlayerEntity;Lnet/minecraft/client/util/math/MatrixStack;F)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;scale(FFF)V"))
    private void scaleInjection(Args args, AbstractClientPlayerEntity abstractClientPlayerEntity, MatrixStack matrixStack, float f) {
        float x = args.get(0);
        float y = args.get(1);
        float z = args.get(2);
        var event = EventManager.callEvent(new PlayerEntityRendererScaleEvent(abstractClientPlayerEntity, x, y, z));
        args.set(0, event.getX());
        args.set(1, event.getY());
        args.set(2, event.getZ());
    }
}
