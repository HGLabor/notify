package de.hglabor.notify.events.server.player

import de.hglabor.notify.events.PlayerEvent
import de.hglabor.notify.mixins.server.MixinServerPlayerEntity
import me.obsilabor.alert.Event
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.server.network.ServerPlayerEntity

/**
 * @see MixinServerPlayerEntity.tick
 */
@Environment(EnvType.SERVER)
class PlayerTickEvent(override val player: ServerPlayerEntity) : Event(), PlayerEvent