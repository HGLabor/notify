package de.hglabor.notify.events.server.player

import de.hglabor.notify.events.PlayerEvent
import me.obsilabor.alert.Cancellable
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.util.math.BlockPos

/**
 * Called when a player tries to break a block.
 *
 * Cancel to prevent.
 */
@Environment(EnvType.SERVER)
class PlayerBreakBlockEvent(override val player: ServerPlayerEntity, val pos: BlockPos) : Cancellable(), PlayerEvent