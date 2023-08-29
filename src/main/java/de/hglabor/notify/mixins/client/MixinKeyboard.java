package de.hglabor.notify.mixins.client;

import de.hglabor.notify.events.client.KeyPressedOnceEvent;
import me.obsilabor.alert.EventManager;
import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public abstract class MixinKeyboard {
    @Shadow
    @Final
    private MinecraftClient client;

    @Inject(method = "onKey", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/InputUtil;fromKeyCode(II)Lnet/minecraft/client/util/InputUtil$Key;", shift = At.Shift.AFTER), cancellable = true)
    private void onKeyInjection(long window, int key, int scancode, int action, int j, CallbackInfo callback) {
        if (action == 1) {
            //Cancel nie getestet
            var evt = EventManager.callEvent(new KeyPressedOnceEvent(InputUtil.fromKeyCode(key, scancode), client));
            if (evt.isCancelled()) {
                callback.cancel();
            }
        }
    }
}