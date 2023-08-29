@file:Suppress("unused")

package de.hglabor.notify.events.client

import me.obsilabor.alert.Cancellable
import me.obsilabor.alert.Event
import net.minecraft.client.MinecraftClient
import net.minecraft.client.util.InputUtil

class MouseButtonEvent(val window: Long, val button: Int, val action: Int, val mods: Int) : Event()
class MouseScrollEvent(val window: Long, val horizontal: Double, val vertical: Double) : Event()
/** Called when the cursor is moved */
class MouseCursorEvent(val window: Long, val x: Double, val y: Double) : Event()
class KeyPressedOnceEvent(val key: InputUtil.Key, val action: Int, val client: MinecraftClient) : Cancellable()