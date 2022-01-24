package com.mertselcukdemir.core.di

import android.content.Context
import com.mertselcukdemir.core.data.repositories.GithubRepoRepository
import com.mertselcukdemir.core.di.modules.ContextModule
import com.mertselcukdemir.core.di.modules.NetworkModule
import com.mertselcukdemir.core.network.services.GithubRepoService
import dagger.Component
import javax.inject.Singleton

/**
 * Created by mertselcukdemir on 20.01.2022
 * All rights reserved.
 */

/**
 * Core component that all module's components depend on.
 *
 * @see Component
 */

@Singleton
@Component(
    modules = [
        ContextModule::class,
        NetworkModule::class
    ]
)
interface CoreComponent {
    /**
     * Provide dependency graph [Context]
     *
     * @return Context
     */
    fun context(): Context

    /**
     * Provide dependency graph [GithubRepoService]
     *
     * @return GithubRepoService
     */
    fun githubRepoService(): GithubRepoService

    /**
     * Provide dependency graph [GithubRepoRepository]
     *
     * @return GithubRepoRepository
     */
    fun githubRepoRepository(): GithubRepoRepository
}