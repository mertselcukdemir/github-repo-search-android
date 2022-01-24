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

    buildTypes.forEach{
        it.buildConfigField("String", "SERVER_URL", "\"https://api.github.com/\"")
    }
}

dependencies {
    implementation(Dependencies.NAVIGATION_UI)
    implementation(Dependencies.LIFECYCLE_EXTENSIONS)
    implementation(Dependencies.FRAGMENT_KTX)
    implementation(Dependencies.CORE_KTX)
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.RETROFIT_CONVERTER)
    implementation(Dependencies.LOGGING)
    implementation(Dependencies.MOSHI)
    implementation(Dependencies.MOSHI_KTX)
}
