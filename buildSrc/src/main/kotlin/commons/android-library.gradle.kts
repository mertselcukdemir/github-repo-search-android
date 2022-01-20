/**
 * Created by mertselcukdemir on 19.01.2022
 * All rights reserved.
 */

package commons

import dependencies.Dependencies
import dependencies.AnnotationProcessorsDependencies

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

dependencies {
    implementation(Dependencies.KOTLIN)
    implementation(Dependencies.COROUTINES)
    implementation(Dependencies.COROUTINES_ANDROID)
    implementation(Dependencies.DAGGER)
    implementation(Dependencies.TIMBER)


    kapt(AnnotationProcessorsDependencies.DAGGER)
}
