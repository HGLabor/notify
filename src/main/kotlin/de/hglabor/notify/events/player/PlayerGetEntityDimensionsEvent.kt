package de.hglabor.notify.events.player

import de.hglabor.notify.events.PlayerEvent
import me.obsilabor.alert.Event
import net.minecraft.entity.EntityDimensions
import net.minecraft.entity.player.PlayerEntity

class PlayerGetEntityDimensionsEvent(override val player: PlayerEntity, var dimensions: EntityDimensions) : Event(),
    PlayerEvent