package de.hglabor.notify.events.server.player

import de.hglabor.notify.events.PlayerEvent
import me.obsilabor.alert.Cancellable
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.screen.slot.SlotActionType

/**
 * Called when a player crafts an item.
 */
class PlayerCraftItemEvent(override val player: PlayerEntity, val item: ItemStack, val action: SlotActionType) : Cancellable(), PlayerEvent