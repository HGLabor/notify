package de.hglabor.notify.events.server.player

import de.hglabor.notify.mixins.server.MixinPlayerManager
import me.obsilabor.alert.Event
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.text.Text

/**
 * Called **during** the player join process. Allows modification of the join message
 * @see MixinPlayerManager.onPlayerConnect
 */
@Environment(EnvType.SERVER)
class PlayerJoinEvent(val player: ServerPlayerEntity, var joinMessage: Text?) : Event()