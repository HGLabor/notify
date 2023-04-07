package de.hglabor.notify.events.server.player

import de.hglabor.notify.mixins.server.MixinPlayerManager
import me.obsilabor.alert.Event
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.server.network.ServerPlayerEntity

/**
 * Called **after** a player joins.
 * @see MixinPlayerManager.onPlayerConnect
 */
@Environment(EnvType.SERVER)
class PlayerJoinEvent(val player: ServerPlayerEntity) : Event()