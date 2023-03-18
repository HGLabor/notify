package de.hglabor.notify.events.player

import me.obsilabor.alert.Cancellable
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemPlacementContext

/**
 * Called when a player tries to place a block.
 *
 * Cancel to prevent.
 */
class PlayerPlaceBlockEvent(val player: PlayerEntity, val context: ItemPlacementContext) : Cancellable()