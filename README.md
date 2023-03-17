# notify

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
plugins {
    // ...
    
    // You probably want to add the shadow jar plugin to shade alert into your mod jar
    id("com.github.johnrengelman.shadow") version "7.1.0"
}

// ...

repositories {
    mavenCentral() // TODO
}

dependencies {
    // ...
    
    implementation("me.obsilabor:alert:1.0.6")
    modImplementation("de.hglabor:notify:1.0.0")
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
<details open>
<summary>Server</summary>

- player join: called _after_ a player joins the server
- player leave: called _after_ a player leaves the server

</details>

<details open>
<summary>Client</summary>

</details>
