package de.hglabor.notify.events.player

import de.hglabor.notify.events.PlayerEvent
import me.obsilabor.alert.Cancellable
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.screen.slot.SlotActionType

/**
 * Called when a player interacts with a slot in the inventory (click, drop, etc.).
 *
 * Cancel to prevent.
 */
class PlayerSlotClickEvent(
    override val player: PlayerEntity,
    val itemStack: ItemStack?,
    val slotId: Int,
    val slotActionType: SlotActionType
) : Cancellable(), PlayerEvent