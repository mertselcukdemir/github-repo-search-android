package com.mertselcukdemir.githubrepo.di

import android.content.Context
import com.mertselcukdemir.githubrepo.GithubRepoApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by mertselcukdemir on 20.01.2022
 * All rights reserved.
 */

/**
 * Class that contributes to the object graph [AppComponent].
 *
 * @see Module
 */
@Module
object AppModule {

    /**
     * Create a provider method binding for [Context].
     *
     * @param application GithubRepoApplication.
     * @return Instance of context.
     */
    @Provides
    @Singleton
    fun provideContext(): Context {
        return GithubRepoApplication.instance.applicationContext
    }
}