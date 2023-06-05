package de.hglabor.notify.events.server.entity

import de.hglabor.notify.events.EntityEvent
import de.hglabor.notify.mixins.MixinProjectileEntity
import me.obsilabor.alert.Event
import net.minecraft.entity.Entity
import net.minecraft.entity.projectile.ProjectileEntity
import net.minecraft.util.hit.EntityHitResult

/**
 * Called after a projectile hits another entity.
 *
 * @param entity The projectile entity.
 * @param hitResult The hit result containing the hit entity and the position.
 *
 * @see MixinProjectileEntity.onEntityCollision
 */
class ProjectileHitEvent(override val entity: ProjectileEntity, val hitResult: EntityHitResult) : Event(), EntityEvent {
    val projectile get() = entity
    val hitEntity: Entity get() = hitResult.entity
}