# 🔔notify

We don't want to create mixins for each project over and over again for the same events we could just use in the good old paper days.
This is why we created this spectacular library/mod, where we just have to add the events once and can use them in every other project!

For this we use [alert](https://github.com/mooziii/alert), a blazing fast event listening utility.
See [alert's documentation](https://github.com/mooziii/alert#tutorial) on how to listen to events.

<details>

<summary>

### How to use

</summary>

Add the following to your mod's `build.gradle.kts` file:
```gradle
repositories {
    mavenCentral()
}

dependencies {
    // ...
    
    modImplementation("de.hglabor:notify:1.0.6")
}
```
Then put the built jar file of notify into your mods folder. You should be able to subscribe to the events like this:
```kotlin
// (On the server)
subscribeToEvent<PlayerJoinEvent> {
    logger.info("Player ${it.player.name.string} joined")
}
```


</details>

### Events currently implemented
Subscribing to events on the client is not tested! (server/common events **should work on the server**)
<details open>

<summary>Common</summary>

- `PlayerAttackEntityEvent`
- `PlayerHungerChangeEvent`
- `PlayerItemPickupEvent`
- `PlayerPlaceBlockEvent`
- `PlayerSlotClickEvent` (also called when player attempts to drop an item in the inventory)
- `PlayerItemCraftEvent`
- `EntityDamageEvent`

</details>

<details open>
<summary>Server</summary>

- `PlayerBreakBlockEvent`
- `PlayerDeathEvent`
- `PlayerInteractItemEvent`
- `PlayerInteractBlockEvent`
- `PlayerItemDropEvent`: called _when_ a player attempts to drop an item
- `PlayerItemDroppedEvent`: called _after_ an item was dropped
- `PrePlayerJoinEvent`: called _before_ a player joins the server
- `PlayerJoinEvent`: called _when_ a player joins the server. Allows modification of the join message
- `PostPlayerJoinEvent`: called _after_ a player joins the server
- `PlayerRemoveEvent`: called _after_ a player got removed from the player list
- `PlayerQuitEvent`: called _when_ a player quits the server. Allows modification of the quit message
- `PlayerSwapHandItemsEvent`
- `PlayerTickEvent`
- `PlayerSetSettingsEvent`: called e.g. when player changes client language
- `EntitySpawnEvent`
</details>

<details open>
<summary>Client</summary>
</details>

<details>
<summary>Every entity (and player) event implements the <code>EntityEvent</code> interface and additionally every player event
implements the <code>PlayerEvent</code> interface. That way we can easily add custom event listeners, e.g. like this:</summary>

```kotlin
inline fun <reified T : Event> customSubscribeToEvent(
    noinline isActiveCallback: () -> Boolean = { true },
    priority: Int = -1,
    noinline handleCallback: (T) -> Unit,
) = subscribeToEvent<T>(isActiveCallback, priority) {
    if (it is EntityEvent && it.entity is PlayerEntity && it.entity.customProperty == "foo") {
        handleCallback(it)
    }
}
```
In this example, when we e.g. use `customSubscribeToEvent<PlayerDeathEvent> {...}` we only listen to player death events
where the player has the `customProperty` set to `"foo"`.

</details>