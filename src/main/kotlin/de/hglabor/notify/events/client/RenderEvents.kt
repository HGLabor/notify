package de.hglabor.notify.events.client

import me.obsilabor.alert.Cancellable

/** Called when the pumpkin overlay is rendered. Cancel to disable the overlay. */
class PumpkinOverlayRenderEvent : Cancellable()