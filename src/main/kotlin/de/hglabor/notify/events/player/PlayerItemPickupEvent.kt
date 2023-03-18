package de.hglabor.notify.events.player

import me.obsilabor.alert.Cancellable
import net.minecraft.entity.ItemEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack

/**
 * Called when a player attempts to pick up an item.
 *
 * Cancel to prevent.
 */
class PlayerItemPickupEvent(val player: PlayerEntity, val stack: ItemStack, val item: ItemEntity) : Cancellable()