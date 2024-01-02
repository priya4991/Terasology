// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0

// This magically allows subdirs to become included builds
// https://docs.gradle.org/6.4.1/userguide/composite_builds.html
File(rootDir, "libs").listFiles()?.filter { it.isDirectory }?.forEach { possibleSubprojectDir ->
    val subprojectName = ":libs:" + possibleSubprojectDir.name
    val buildFile = File(possibleSubprojectDir, "build.gradle")
    val settingsFile = File(possibleSubprojectDir, "settings.gradle")
    if (!buildFile.exists()) {
        logger.warn("***** WARNING: Found a lib without a build.gradle, corrupt dir? NOT including $subprojectName *****")
        return@forEach
    }
    if (!settingsFile.exists()) {
        logger.warn("lib $subprojectName has build.gradle, but no settins.gradle? NOT including $subprojectName")
        return@forEach
    }
    logger.info("lib $subprojectName has a build file so counting it complete and including it")
    include(subprojectName)
}
