package de.hglabor.notify.events

import net.minecraft.entity.Entity
import net.minecraft.entity.player.PlayerEntity

interface PlayerEvent : EntityEvent {
    val player: PlayerEntity
    override val entity: Entity get() = player
}