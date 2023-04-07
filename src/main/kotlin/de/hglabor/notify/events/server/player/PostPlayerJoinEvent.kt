package de.hglabor.notify.events.server.player

import de.hglabor.notify.mixins.server.MixinPlayerManager
import me.obsilabor.alert.Event
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.server.network.ServerPlayerEntity

/**
 * Called **after** a player joins. Doesn't allow modification of the join message
 * @see MixinPlayerManager.onPlayerConnectPost
 */
@Environment(EnvType.SERVER)
class PostPlayerJoinEvent(val player: ServerPlayerEntity) : Event()