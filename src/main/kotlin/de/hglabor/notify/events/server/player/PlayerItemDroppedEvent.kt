package de.hglabor.notify.events.server.player

import de.hglabor.notify.mixins.server.MixinServerPlayerEntity
import me.obsilabor.alert.Event
import net.minecraft.entity.ItemEntity
import net.minecraft.server.network.ServerPlayerEntity

/**
 * Called after an item was dropped.
 * Called by [MixinServerPlayerEntity.injectDropItem].
 */
class PlayerItemDroppedEvent(val player: ServerPlayerEntity, val item: ItemEntity) : Event()