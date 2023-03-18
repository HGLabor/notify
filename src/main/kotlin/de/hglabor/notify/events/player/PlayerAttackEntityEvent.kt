package de.hglabor.notify.events.player

import me.obsilabor.alert.Cancellable
import net.minecraft.entity.Entity
import net.minecraft.entity.player.PlayerEntity

/**
 * Called when a player attempts to attack an entity.
 *
 * Cancel to prevent.
 */
class PlayerAttackEntityEvent(val player: PlayerEntity, val target: Entity) : Cancellable()