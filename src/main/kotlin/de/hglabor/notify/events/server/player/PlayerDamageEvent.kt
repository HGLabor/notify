package de.hglabor.notify.events.server.player

import me.obsilabor.alert.Cancellable
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.entity.damage.DamageSource
import net.minecraft.server.network.ServerPlayerEntity

/**
 * Called when a player gets damaged.
 *
 * Cancel to prevent. When cancelled, the damage will be set to [newAmount] (default 0f).
 */
@Environment(EnvType.SERVER)
class PlayerDamageEvent(val player: ServerPlayerEntity, val source: DamageSource, val amount: Float) : Cancellable() {
    var newAmount = 0f // Applied when cancelled
}