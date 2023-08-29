package de.hglabor.notify.events.client

import de.hglabor.notify.events.PlayerEvent
import me.obsilabor.alert.Event
import net.minecraft.entity.player.PlayerEntity

/**
 * Change the scale of the rendered player entity by changing the [x], [y] and [z] values.
 *
 * Default values are 0.9375F, 0.9375F, 0.9375F.
 */
class PlayerEntityRendererScaleEvent(override val player: PlayerEntity, var x: Float, var y: Float, var z: Float) :
    Event(), PlayerEvent