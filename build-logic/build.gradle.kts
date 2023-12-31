// Copyright 2022 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0

import java.net.URI

plugins {
    `kotlin-dsl`
}

kotlin {
    jvmToolchain(17)
}

repositories {
    mavenCentral()
    google()  // gestalt uses an annotation package by Google
    gradlePluginPortal()

    maven {
        name = "Terasology Artifactory"
        url = URI("https://artifactory.terasology.io/artifactory/virtual-repo-live")
    }
}

dependencies {
    implementation("org.terasology:reflections:0.9.12-MB") {
        because("reflections-manifest.gradle.kts")
    }
    // Additional corrections for old reflections dependencies:
    constraints {
        implementation("com.google.guava:guava:31.1-jre")
        implementation("org.javassist:javassist:3.29.0-GA")
        implementation("net.bytebuddy:bytebuddy:1.14.8")
    }

    // graph analysis
    implementation("org.jgrapht:jgrapht-core:1.5.0")

    // for inspecting modules
    implementation("org.terasology.gestalt:gestalt-module:7.1.0")

    // plugins we configure
    implementation("com.github.spotbugs.snom:spotbugs-gradle-plugin:5.2.3")
    implementation("org.sonarsource.scanner.gradle:sonarqube-gradle-plugin:4.4.1.3373")

    api(kotlin("test"))
}

group = "org.terasology.gradology"
