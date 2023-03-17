package de.hglabor.notify

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.DedicatedServerModInitializer
import net.fabricmc.api.ModInitializer
import org.apache.logging.log4j.LogManager

val logger = LogManager.getLogger("notify")

object Manager: ModInitializer, DedicatedServerModInitializer, ClientModInitializer {

    override fun onInitialize() {
        // Common initialization
    }

    override fun onInitializeClient() {
        // Client initialization
    }

    override fun onInitializeServer() {
        // Dedicated server initialization
    }
}