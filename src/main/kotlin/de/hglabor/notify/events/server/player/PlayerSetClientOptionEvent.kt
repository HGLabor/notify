package de.hglabor.notify.events.server.player

import de.hglabor.notify.events.PlayerEvent
import me.obsilabor.alert.Event
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.network.packet.c2s.common.SyncedClientOptions
import net.minecraft.server.network.ServerPlayerEntity

/**
 * Called when the player's settings (e.g. language) are changed due to a client packet.
 */
@Environment(EnvType.SERVER)
class PlayerSetClientOptionEvent(override val player: ServerPlayerEntity, val clientOption: SyncedClientOptions) : Event(), PlayerEvent