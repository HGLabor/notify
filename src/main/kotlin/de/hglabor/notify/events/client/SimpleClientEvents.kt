package de.hglabor.notify.events.client

import me.obsilabor.alert.Event

class ClientStopEvent : Event()

class PreTickEvent : Event()

class PostTickEvent : Event()

/** Called when client disconnected from (integrated) server */
class GameDisconnectEvent : Event()

/** Called when client connected to (integrated) server */
class GameJoinEvent : Event()