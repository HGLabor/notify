package de.hglabor.notify.events.server.player

import de.hglabor.notify.events.PlayerEvent
import me.obsilabor.alert.Cancellable
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.server.network.ServerPlayerEntity

/**
 * Called when a player would die.
 *
 * Cancel to prevent.
 * Note that you will still have to handle the player stuff (reset health etc.) yourself when canceled.
 */
@Environment(EnvType.SERVER)
class PlayerDeathEvent(override val player: ServerPlayerEntity) : Cancellable(), PlayerEvent