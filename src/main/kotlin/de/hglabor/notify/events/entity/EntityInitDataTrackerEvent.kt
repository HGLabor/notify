package de.hglabor.notify.events.entity

import de.hglabor.notify.events.EntityEvent
import me.obsilabor.alert.Cancellable
import net.minecraft.entity.Entity

class EntityInitDataTrackerEvent(override val entity: Entity) : Cancellable(), EntityEvent