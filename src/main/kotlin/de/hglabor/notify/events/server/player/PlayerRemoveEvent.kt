package de.hglabor.notify.events.server.player

import de.hglabor.notify.events.PlayerEvent
import de.hglabor.notify.mixins.server.MixinPlayerManager
import me.obsilabor.alert.Event
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.server.network.ServerPlayerEntity

/**
 * Called **after** a player got removed from the player list.
 * @see MixinPlayerManager.remove
 */
@Environment(EnvType.SERVER)
class PlayerRemoveEvent(override val player: ServerPlayerEntity) : Event(), PlayerEvent