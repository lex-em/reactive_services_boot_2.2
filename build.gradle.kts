plugins {
    val kotlinVersion = "1.3.50"
    kotlin("jvm") version kotlinVersion apply false
    kotlin("plugin.spring") version kotlinVersion  apply false
    kotlin("plugin.noarg") version kotlinVersion  apply false
//    `java`
    id("io.spring.dependency-management") version "1.0.7.RELEASE"  apply false
    id("org.springframework.boot") version "2.2.1.RELEASE"  apply false
}

subprojects {
    group = "ru.reliabletech.java_chel"
    version = "0.1.0-SNAPSHOT"

    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.kapt")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.jetbrains.kotlin.plugin.noarg")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.springframework.boot")

    repositories {
//        maven("https://repo.spring.io/milestone")
        mavenCentral()
    }
}
