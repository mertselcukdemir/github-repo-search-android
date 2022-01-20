package com.mertselcukdemir.githubrepo.di

import com.mertselcukdemir.core.di.CoreComponent
import com.mertselcukdemir.core.di.scopes.AppScope
import com.mertselcukdemir.githubrepo.GithubRepoApplication
import dagger.Component

/**
 * Created by mertselcukdemir on 20.01.2022
 * All rights reserved.
 */

/**
 * App component that application component's components depend on.
 *
 * @see Component
 */
@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class]
)
interface AppComponent {
    /**
     * Inject dependencies on application.
     *
     * @param application The sample application.
     */
    fun inject(application: GithubRepoApplication)
}