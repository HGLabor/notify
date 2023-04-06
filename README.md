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
    
    implementation("me.obsilabor:alert:1.0.6")
    modImplementation("de.hglabor:notify:1.0.2")
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
- `PlayerSlotClickEvent`

</details>

<details open>
<summary>Server</summary>

- `PlayerBreakBlockEvent`
- `PlayerDeathEvent`
- `PlayerInteractItemEvent`
- `PlayerItemDropEvent`
- `PlayerItemDroppedEvent`
- `PlayerJoinEvent`: called _after_ a player joins the server
- `PlayerLeaveEvent`: called _after_ a player leaves the server
- `PlayerSwapHandItemsEvent`
- `PlayerTickEvent`

</details>

<details open>
<summary>Client</summary>

</details>
