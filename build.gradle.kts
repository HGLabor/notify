val javaVersion = 17
val repo = "HGLabor/notify"

plugins {
    kotlin("jvm") version "1.8.20"
    id("fabric-loom") version "1.1-SNAPSHOT"
    id("maven-publish")
    id("signing")
}

group = "de.hglabor"
version = "1.2.2"

val minecraftVersion = "1.20.1"
val yarnMappings = "1.20.1+build.1"
val loaderVersion = "0.14.21"
val fabricVersion = "0.83.0+1.20.1"

repositories {
    mavenCentral()
}

dependencies {
    minecraft("com.mojang:minecraft:$minecraftVersion")
    mappings("net.fabricmc:yarn:$yarnMappings")
    modImplementation("net.fabricmc:fabric-loader:$loaderVersion")
    modImplementation("net.fabricmc.fabric-api:fabric-api:$fabricVersion")
    modImplementation("net.fabricmc:fabric-language-kotlin:1.9.3+kotlin.1.8.20")

    include(api("me.obsilabor:alert:1.0.7")!!)
}

tasks {
    compileKotlin {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjdk-release=$javaVersion", "-Xskip-prerelease-check")
            jvmTarget = "$javaVersion"
        }
    }
    compileJava {
        options.encoding = "UTF-8"
        options.release.set(javaVersion)
    }
    processResources {
        val properties = mapOf("version" to project.version)
        inputs.properties(properties)
        filesMatching("fabric.mod.json") { expand(properties) }
    }
}

java {
    withSourcesJar()
    withJavadocJar()
}

signing {
    sign(publishing.publications)
}

publishing {
    repositories {
        maven("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/") {
            name = "ossrh"
            credentials(PasswordCredentials::class)
        }
    }
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])

            this.groupId = project.group.toString()
            this.artifactId = project.name.lowercase()
            this.version = project.version.toString()

            pom {
                name.set(project.name)
                description.set("Notify is a collection of fabric mixins for paper events (and some more).")

                developers {
                    developer {
                        name.set("krxwallo")
                        url.set("https://github.com/Krxwallo")
                    }
                    developer {
                        name.set("mooziii")
                        url.set("https://github.com/mooziii")
                    }
                }

                licenses {
                    license {
                        name.set("MIT")
                        url.set("https://github.com/$repo/blob/main/LICENSE")
                    }
                }

                url.set("https://github.com/$repo")

                scm {
                    connection.set("scm:git:git://github.com/$repo.git")
                    url.set("https://github.com/$repo/tree/main")
                }
            }
        }
    }
}
