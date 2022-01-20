package dependencies

/**
 * Created by mertselcukdemir on 19.01.2022
 * All rights reserved.
 */

/**
 * Project dependencies, makes it easy to include external binaries or
 * other library modules to build.
 */
object Dependencies {
        const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${BuildDependenciesVersions.KOTLIN}"
        const val APPCOMPAT = "androidx.appcompat:appcompat:${BuildDependenciesVersions.APPCOMPAT}"
        const val MATERIAL = "com.google.android.material:material:${BuildDependenciesVersions.MATERIAL}"
        const val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${BuildDependenciesVersions.COROUTINES}"
        const val COROUTINES_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${BuildDependenciesVersions.COROUTINES}"
        const val RECYCLER_VIEW = "androidx.recyclerview:recyclerview:${BuildDependenciesVersions.RECYCLER_VIEW}"
        const val CARD_VIEW = "androidx.cardview:cardview:${BuildDependenciesVersions.CARD_VIEW}"
        const val NAVIGATION_FRAGMENT = "androidx.navigation:navigation-fragment-ktx:${BuildDependenciesVersions.NAVIGATION}"
        const val NAVIGATION_UI = "androidx.navigation:navigation-ui-ktx:${BuildDependenciesVersions.NAVIGATION}"
        const val LIFECYCLE_EXTENSIONS = "androidx.lifecycle:lifecycle-extensions:${BuildDependenciesVersions.LIFECYCLE}"
        const val LIFECYCLE_VIEWMODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:${BuildDependenciesVersions.LIFECYCLE}"
        const val CORE_KTX = "androidx.core:core-ktx:${BuildDependenciesVersions.CORE_KTX}"
        const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx:${BuildDependenciesVersions.FRAGMENT_KTX}"
        const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${BuildDependenciesVersions.CONSTRAINT_LAYOUT}"
        const val DAGGER = "com.google.dagger:dagger:${BuildDependenciesVersions.DAGGER}"
        const val TIMBER = "com.jakewharton.timber:timber:${BuildDependenciesVersions.TIMBER}"
        const val RETROFIT = "com.squareup.retrofit2:retrofit:${BuildDependenciesVersions.RETROFIT}"
        const val RETROFIT_CONVERTER = "com.squareup.retrofit2:converter-moshi:${BuildDependenciesVersions.RETROFIT}"
        const val LOGGING = "com.squareup.okhttp3:logging-interceptor:${BuildDependenciesVersions.LOGGING}"
        const val MOSHI = "com.squareup.moshi:moshi:${BuildDependenciesVersions.MOSHI}"
        const val MOSHI_KTX = "com.squareup.moshi:moshi-kotlin:${BuildDependenciesVersions.MOSHI}"
        const val COIL = "io.coil-kt:coil:${BuildDependenciesVersions.COIL}"
        const val PLAY_CORE = "com.google.android.play:core:${BuildDependenciesVersions.PLAY_CORE}"
}