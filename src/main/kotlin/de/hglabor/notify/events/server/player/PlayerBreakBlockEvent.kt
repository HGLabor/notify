package de.hglabor.notify.events.server.player

import me.obsilabor.alert.Cancellable
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.util.math.BlockPos

/**
 * Called when a player tries to break a block.
 *
 * Cancel to prevent.
 */
class PlayerBreakBlockEvent(val player: ServerPlayerEntity, val pos: BlockPos) : Cancellable()