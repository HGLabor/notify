package de.hglabor.notify.events.entity

import de.hglabor.notify.events.EntityEvent
import me.obsilabor.alert.Event
import net.minecraft.entity.Entity
import net.minecraft.entity.data.TrackedData

class EntityOnTrackedDataSetEvent(override val entity: Entity, val data: TrackedData<*>) : Event(), EntityEvent