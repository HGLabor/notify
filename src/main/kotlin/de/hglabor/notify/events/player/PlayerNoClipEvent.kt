package de.hglabor.notify.events.player

import de.hglabor.notify.events.PlayerEvent
import me.obsilabor.alert.Event
import net.minecraft.entity.player.PlayerEntity

class PlayerNoClipEvent(override val player: PlayerEntity, var noClip: Boolean) : Event(), PlayerEvent