package de.hglabor.notify.events.server.player

import de.hglabor.notify.events.PlayerEvent
import me.obsilabor.alert.Cancellable
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.util.Hand
import net.minecraft.util.hit.BlockHitResult

/**
 * Called when a player interacts with a block.
 *
 * Cancel to prevent vanilla behaviour (e.g. opening a crafting table ui).
 */
@Environment(EnvType.SERVER)
class PlayerInteractBlockEvent(override val player: ServerPlayerEntity, val hand: Hand, val blockHitResult: BlockHitResult) : Cancellable(), PlayerEvent