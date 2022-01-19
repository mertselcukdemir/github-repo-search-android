/**
 * Created by mertselcukdemir on 19.01.2022
 * All rights reserved.
 */

import dependencies.Dependencies
import dependencies.implementation

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
    implementation(Dependencies.LIFECYCLE_EXTENSIONS)
    implementation(Dependencies.LIFECYCLE_VIEWMODEL)
    implementation(Dependencies.CONSTRAINT_LAYOUT)
    implementation(Dependencies.RECYCLER_VIEW)
    implementation(Dependencies.CORE_KTX)
    implementation(Dependencies.FRAGMENT_KTX)
    implementation(Dependencies.NAVIGATION_FRAGMENT)
    implementation(Dependencies.NAVIGATION_UI)
    implementation(Dependencies.COIL)
    //kapt(AnnotationProcessorsDependencies.GLIDE)
}
repositories {
    mavenCentral()
}