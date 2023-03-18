package de.hglabor.notify.events.server.player

import de.hglabor.notify.mixins.server.MixinServerPlayerEntity
import me.obsilabor.alert.Event
import net.minecraft.server.network.ServerPlayerEntity

/**
 * @see MixinServerPlayerEntity.tick
 */
class PlayerTickEvent(val player: ServerPlayerEntity) : Event()