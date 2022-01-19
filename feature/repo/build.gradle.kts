/**
 * Created by mertselcukdemir on 19.01.2022
 * All rights reserved.
 */

import dependencies.Dependencies

plugins {
    id("commons.android-dynamic-feature")
}

android {
    compileSdk = BuildAndroidConfig.COMPILE_SDK_VERSION
    defaultConfig {
        minSdk = BuildAndroidConfig.MIN_SDK_VERSION
    }

    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(project(BuildModules.Commons.VIEW))
    implementation(project(BuildModules.Commons.UI))

    implementation(Dependencies.RECYCLER_VIEW)
    implementation(Dependencies.CARD_VIEW)
    implementation(Dependencies.RETROFIT)
}