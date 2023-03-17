import net.minecraftforge.gradle.userdev.UserDevExtension
import org.gradle.jvm.tasks.Jar

// gradle.properties
val modGroup = "com.ddang_"
val modVersion = "1.0-SNAPSHOT"
val modBaseName = "BloodyUtility"
val forgeVersion: String by extra
val mappingVersion: String by extra

buildscript {
    repositories {
        mavenCentral()
        maven(url = "https://maven.minecraftforge.net/")
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots")
    }
    dependencies {
        classpath("net.minecraftforge.gradle:ForgeGradle:5.1.+") {
            isChanging = true
        }
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.0")
    }
}

plugins {
    java
    scala
}

apply {
    plugin("net.minecraftforge.gradle")
    plugin("kotlin")
}

version = modVersion
group = modGroup

configure<UserDevExtension> {
    mappings("stable",  "39-1.12")
    accessTransformer(file("src/main/resources/META-INF/accesstransformer.cfg"))
    runs {
        create("client") {
            workingDirectory(project.file("run"))
            property("forge.logging.markers", "SCAN,REGISTRIES,REGISTRYDUMP")
            property("forge.logging.console.level", "debug")
        }

        create("server") {
            workingDirectory(project.file("run"))
            property("forge.logging.markers", "SCAN,REGISTRIES,REGISTRYDUMP")
            property("forge.logging.console.level", "debug")
        }
    }
}

repositories {
    jcenter()
    mavenCentral()
    maven(url = "https://maven.shadowfacts.net/")
}

dependencies {
    "minecraft"("net.minecraftforge:forge:1.12.2-14.23.5.2860")
    compileOnly("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.0")
    compileOnly("net.shadowfacts:Forgelin:1.8.4")
}

val Project.minecraft: UserDevExtension
    get() = extensions.getByName<UserDevExtension>("minecraft")
tasks.withType<Jar> {
    inputs.property("version", project.version)

    baseName = modBaseName

    filesMatching("/mcmod.info") {
        expand(mapOf(
            "version" to project.version,
            "mcversion" to "1.12.2"
        ))
    }
}

tasks.create("copyResourceToClasses", Copy::class) {
    tasks.classes.get().dependsOn(this)
    dependsOn(tasks.processResources.get())
    onlyIf { gradle.taskGraph.hasTask(tasks.getByName("prepareRuns")) }
    into("$buildDir/classes/kotlin/main")
    from(tasks.processResources.get().destinationDir)
}



/*plugins {
    id("net.minecraftforge.gradle")// version "5.1"
    kotlin("jvm") version "1.8.0"
}

group = "com.ddang_"
version = "1.0-SNAPSHOT"

minecraft {
    mappings("stable", "39-1.12")

    runs {
        create("client") {
            workingDirectory(project.file("run"))
            property("forge.logging.markers", "REGISTRIES")
            property("forge.logging.console.level", "debug")
        }

        create("server") {
            workingDirectory(project.file("run"))
            property("forge.logging.markers", "REGISTRIES")
            property("forge.logging.console.level", "debug")
        }
    }
}

sourceSets.main.resources { srcDir "src/generated/resources" }

repositories {
    mavenCentral()
}

dependencies {
    minecraft("net.minecraftforge:forge:1.12.2-14.23.5.2860")
    implementation ("org.jetbrains.kotlin:kotlin-stdlib")
}

jar {
    manifest {
        attributes(listOf(
                "Specification-Title"     : "bloodyutility",
                "Specification-Vendor"    : "DDang_",
                "Specification-Version"   : "1", // We are version 1 of ourselves
                "Implementation-Title"    : project.name,
                "Implementation-Version"  : project.jar.archiveVersion,
                "Implementation-Vendor"   : "DDang_",
                "Implementation-Timestamp": new Date().format("yyyy-MM-dd'T'HH:mm:ssZ")
        ))
    }
}

jar.finalizedBy("reobfJar")

tasks.withType(JavaCompile) {
    options.encoding = "UTF-8"
}*/
