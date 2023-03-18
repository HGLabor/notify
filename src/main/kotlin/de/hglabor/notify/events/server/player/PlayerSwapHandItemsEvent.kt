package de.hglabor.notify.events.server.player

import me.obsilabor.alert.Cancellable
import net.minecraft.server.network.ServerPlayerEntity

/**
 * Called when a player tries to swap the main hand item with the offhand item.
 *
 * Cancel to prevent.
 */
class PlayerSwapHandItemsEvent(val player: ServerPlayerEntity) : Cancellable()