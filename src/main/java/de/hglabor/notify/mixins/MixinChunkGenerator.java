package de.hglabor.notify.mixins;

import de.hglabor.notify.events.world.ChunkAfterFeatureGenerationEvent;
import me.obsilabor.alert.EventManager;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChunkGenerator.class)
public abstract class MixinChunkGenerator {
    @Inject(method = "generateFeatures", at = @At("TAIL"))
    private void generateFeaturesInjection(StructureWorldAccess structureWorldAccess, Chunk chunk, StructureAccessor structureAccessor, CallbackInfo ci) {
        EventManager.callEvent(new ChunkAfterFeatureGenerationEvent(chunk));
    }
}
