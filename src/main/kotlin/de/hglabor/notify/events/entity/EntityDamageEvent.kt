package de.hglabor.notify.events.entity

import de.hglabor.notify.events.EntityEvent
import me.obsilabor.alert.Cancellable
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource

/**
 * Called when a living entity gets damaged.
 *
 * Cancel to prevent.
 */
class EntityDamageEvent(override val entity: LivingEntity, val source: DamageSource, val amount: Float) : Cancellable(), EntityEvent