package de.hglabor.notify.events.player

import me.obsilabor.alert.Cancellable
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.screen.slot.SlotActionType

/**
 * Called when a player interacts with a slot in the inventory (click, drop, etc.).
 *
 * Cancel to prevent.
 */
class PlayerSlotClickEvent(val player: PlayerEntity, val slotId: Int, val slotActionType: SlotActionType) : Cancellable()