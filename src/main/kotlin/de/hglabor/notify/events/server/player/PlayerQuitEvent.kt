package de.hglabor.notify.events.server.player

import de.hglabor.notify.events.PlayerEvent
import de.hglabor.notify.mixins.server.MixinServerPlayNetworkHandler
import me.obsilabor.alert.Event
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.text.Text

/**
 * Called **during** the player quit process. Allows modification of the quit message
 * @see MixinServerPlayNetworkHandler.onPlayerDisconnect
 */
@Environment(EnvType.SERVER)
class PlayerQuitEvent(override val player: ServerPlayerEntity, var quitMessage: Text?) : Event(), PlayerEvent