package com.mertselcukdemir.githubrepo

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Created by mertselcukdemir on 20.01.2022
 * All rights reserved.
 */

@HiltAndroidApp
class GithubRepoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: GithubRepoApplication
            private set
    }
}