package com.mertselcukdemir.githubrepo

import android.app.Application
import android.content.Context
import com.mertselcukdemir.core.di.CoreComponent
import com.mertselcukdemir.core.di.DaggerCoreComponent
import com.mertselcukdemir.core.di.modules.ContextModule
import com.mertselcukdemir.githubrepo.di.DaggerAppComponent

/**
 * Created by mertselcukdemir on 20.01.2022
 * All rights reserved.
 */

class GithubRepoApplication : Application() {
    lateinit var coreComponent: CoreComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        initCoreDependencyInjection()
        initAppDependencyInjection()
    }

    companion object {
        lateinit var instance: GithubRepoApplication
            private set
        /**
         * Obtain core dagger component.
         *
         * @param context The application context
         */
        @JvmStatic
        fun coreComponent(context: Context) =
            (context.applicationContext as? GithubRepoApplication)?.coreComponent
    }

    // ============================================================================================
    //  Private init methods
    // ============================================================================================

    /**
     * Initialize app dependency injection component.
     */
    private fun initAppDependencyInjection() {
        DaggerAppComponent
            .builder()
            .coreComponent(coreComponent)
            .build()
            .inject(this)
    }

    /**
     * Initialize core dependency injection component.
     */
    private fun initCoreDependencyInjection() {
        coreComponent = DaggerCoreComponent
            .builder()
            .contextModule(ContextModule(this))
            .build()
    }
}