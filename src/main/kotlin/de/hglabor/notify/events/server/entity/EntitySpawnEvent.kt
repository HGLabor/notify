package de.hglabor.notify.events.server.entity

import de.hglabor.notify.events.EntityEvent
import de.hglabor.notify.mixins.server.MixinServerEntityManager
import me.obsilabor.alert.Cancellable
import net.minecraft.entity.Entity

/**
 * Called when an entity is spawned.
 *
 * @param entity The entity that is spawned
 * @param loadedFromWorld When true the entity is loaded from the map and not created anew
 *
 * @see MixinServerEntityManager
 */
class EntitySpawnEvent(override val entity: Entity, val loadedFromWorld: Boolean) : Cancellable(), EntityEvent