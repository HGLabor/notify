package de.hglabor.notify.events.server.player

import de.hglabor.notify.events.PlayerEvent
import me.obsilabor.alert.Cancellable
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.server.network.ServerPlayerEntity

/**
 * Called when a player tries to swap the main hand item with the offhand item.
 *
 * Cancel to prevent.
 */
@Environment(EnvType.SERVER)
class PlayerSwapHandItemsEvent(override val player: ServerPlayerEntity) : Cancellable(), PlayerEvent