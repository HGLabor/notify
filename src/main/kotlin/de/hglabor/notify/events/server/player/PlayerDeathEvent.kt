package de.hglabor.notify.events.server.player

import me.obsilabor.alert.Cancellable
import net.minecraft.server.network.ServerPlayerEntity

/**
 * Called when a player would die.
 *
 * Cancel to prevent.
 * Note that you will still have to handle the player stuff (reset health etc.) yourself when canceled.
 */
class PlayerDeathEvent(val player: ServerPlayerEntity) : Cancellable()