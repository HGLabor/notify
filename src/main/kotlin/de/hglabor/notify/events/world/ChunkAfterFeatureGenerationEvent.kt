package de.hglabor.notify.events.world

import de.hglabor.notify.events.ChunkEvent
import me.obsilabor.alert.Event
import net.minecraft.world.chunk.Chunk

class ChunkAfterFeatureGenerationEvent(
    override val chunk: Chunk,
) : Event(), ChunkEvent