package de.hglabor.notify.events.server.player

import de.hglabor.notify.events.player.PlayerSlotClickEvent
import de.hglabor.notify.mixins.server.MixinServerPlayerEntity
import me.obsilabor.alert.Cancellable
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.item.ItemStack
import net.minecraft.server.network.ServerPlayerEntity

/**
 * Called when a player attempts to drop an item.
 *
 * Note that for dropping in the inventory screen, this event is not called. Instead [PlayerSlotClickEvent] is called.
 *
 * Called by [MixinServerPlayerEntity.dropSelectedItem].
 *
 * Cancel to prevent.
 */
@Environment(EnvType.SERVER)
class PlayerItemDropEvent(val player: ServerPlayerEntity, val stack: ItemStack) : Cancellable()