/**
 * Created by mertselcukdemir on 19.01.2022
 * All rights reserved.
 */

package commons

import BuildAndroidConfig
import BuildProductDimensions
import dependencies.Dependencies
import dependencies.AnnotationProcessorsDependencies

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

dependencies {
    implementation(Dependencies.KOTLIN)
    implementation(Dependencies.COROUTINES)
    implementation(Dependencies.COROUTINES_ANDROID)
    implementation(Dependencies.HILT)
    implementation(Dependencies.TIMBER)


    kapt(AnnotationProcessorsDependencies.HILT)
}
