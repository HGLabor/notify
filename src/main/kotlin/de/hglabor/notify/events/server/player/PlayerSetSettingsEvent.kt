package de.hglabor.notify.events.server.player

import me.obsilabor.alert.Event
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.network.packet.c2s.play.ClientSettingsC2SPacket
import net.minecraft.server.network.ServerPlayerEntity

/**
 * Called when the player's settings (e.g. langauge) are changed via a packet from the client.
 */
@Environment(EnvType.SERVER)
class PlayerSetSettingsEvent(val player: ServerPlayerEntity, val settingsPacket: ClientSettingsC2SPacket) : Event()