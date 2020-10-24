import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.10"
}

group = "org.liamjd.pi"
version = "0.1-SNAPSHOT"


repositories {
    mavenCentral()
    maven {
        name = "Sonatype OSS Maven Repository"
        url = uri("https://oss.sonatype.org/content/groups/public")
        mavenContent {
            snapshotsOnly()
        }
    }
}



dependencies {
    implementation(kotlin("stdlib"))

    // logging
    implementation("org.slf4j:slf4j-api:2.0.0-alpha1")
    implementation("org.slf4j:slf4j-simple:2.0.0-alpha1")

    // pi4j GPIO libraries
    implementation("com.pi4j:pi4j-core:2.0-SNAPSHOT")
    implementation("com.pi4j:pi4j-plugin-raspberrypi:2.0-SNAPSHOT")
    implementation("com.pi4j:pi4j-plugin-pigpio:2.0-SNAPSHOT")
    implementation("com.pi4j:pi4j-library-pigpio:2.0-SNAPSHOT")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_11.toString()
}

tasks.withType<Jar> {
    archiveFileName.set("LightHarp.jar")
    manifest {
        attributes("Main-Class" to "org.liamjd.pi.LightHarp")
    }
    from(configurations.compileClasspath.map { config -> config.map { if (it.isDirectory) it else zipTree(it) } })
}

