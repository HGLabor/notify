package de.hglabor.notify.events.server.player

import de.hglabor.notify.events.PlayerEvent
import me.obsilabor.alert.Cancellable
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.item.ItemStack
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.util.Hand

/**
 * Called when a player interacts with an item.
 *
 * Cancel to prevent vanilla behaviour (e.g. actually *eating* a soup).
 * Note that you still have to handle the item (removal etc.) yourself when canceled.
 */
@Environment(EnvType.SERVER)
class PlayerInteractItemEvent(override val player: ServerPlayerEntity, val stack: ItemStack, val hand: Hand) : Cancellable(), PlayerEvent