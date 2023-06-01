package de.hglabor.notify.events.server.player

import de.hglabor.notify.events.PlayerEvent
import de.hglabor.notify.mixins.server.MixinPlayerManager
import me.obsilabor.alert.Event
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.server.network.ServerPlayerEntity

/**
 * Called **before** a player joins. Doesn't allow modification of the join message
 * @see MixinPlayerManager.onPlayerConnectPre
 */
@Environment(EnvType.SERVER)
class PrePlayerJoinEvent(override val player: ServerPlayerEntity) : Event(), PlayerEvent