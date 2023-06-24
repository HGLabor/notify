package de.hglabor.notify.events

import net.minecraft.world.chunk.Chunk

interface ChunkEvent {
    val chunk: Chunk
}