package de.hglabor.notify.events.player

import de.hglabor.notify.events.PlayerEvent
import me.obsilabor.alert.Cancellable
import net.minecraft.entity.player.PlayerEntity

/**
 * Called when a player's hunger changes.
 *
 * Cancel to prevent. Here you can e.g. reset the hunger to 20.
 */
class PlayerHungerChangeEvent(override val player: PlayerEntity, val oldValue: Int, val newValue: Int) : Cancellable(), PlayerEvent