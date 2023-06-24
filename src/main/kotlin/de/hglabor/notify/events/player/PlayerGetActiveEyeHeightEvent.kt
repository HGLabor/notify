package de.hglabor.notify.events.player

import de.hglabor.notify.events.PlayerEvent
import me.obsilabor.alert.Event
import net.minecraft.entity.EntityDimensions
import net.minecraft.entity.EntityPose
import net.minecraft.entity.player.PlayerEntity

class PlayerGetActiveEyeHeightEvent(
    override val player: PlayerEntity,
    val dimensions: EntityDimensions,
    val pose: EntityPose,
    var eyeHeight: Float
) : Event(),
    PlayerEvent