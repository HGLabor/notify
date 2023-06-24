package de.hglabor.notify.events.client

import de.hglabor.notify.events.PlayerEvent
import me.obsilabor.alert.Event
import net.minecraft.entity.player.PlayerEntity

class PlayerEntityRendererScaleEvent(override val player: PlayerEntity, var x: Float, var y: Float, var z: Float) :
    Event(), PlayerEvent