package de.hglabor.notify.events.client

import me.obsilabor.alert.Cancellable
import net.minecraft.client.MinecraftClient

class KeyPressedOnceEvent(val key: Int, val scanCode: Int, val client: MinecraftClient) : Cancellable()