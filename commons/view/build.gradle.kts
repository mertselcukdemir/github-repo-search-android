/**
 * Created by mertselcukdemir on 19.01.2022
 * All rights reserved.
 */

import dependencies.Dependencies

plugins {
    id("commons.android-library")
}

android {
    compileSdk = BuildAndroidConfig.COMPILE_SDK_VERSION
    defaultConfig {
        minSdk = BuildAndroidConfig.MIN_SDK_VERSION
        targetSdk = BuildAndroidConfig.TARGET_SDK_VERSION
    }
    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(project(BuildModules.Commons.UI))

    implementation(Dependencies.CONSTRAINT_LAYOUT)
    implementation(Dependencies.NAVIGATION_FRAGMENT)
    implementation(Dependencies.NAVIGATION_UI)
    implementation(Dependencies.FRAGMENT_KTX)
}
repositories {
    mavenCentral()
}