package com.mertselcukdemir.repo.ui.detail.di

import com.mertselcukdemir.core.data.repositories.GithubRepoRepository
import com.mertselcukdemir.core.di.scopes.FeatureScope
import com.mertselcukdemir.repo.ui.detail.RepoDetailFragment
import com.mertselcukdemir.repo.ui.detail.RepoDetailViewModel
import dagger.Module
import dagger.Provides
import com.mertselcukdemir.ui.extensions.viewModel

/**
 * Created by mertselcukdemir on 24.01.2022
 * All rights reserved.
 */

/**
 * Class that contributes to the object graph [RepoDetailComponent].
 *
 * @see Module
 */
@Module
class RepoDetailModule(private val fragment: RepoDetailFragment) {
    /**
     * Create a provider method binding for [RepoDetailViewModel].
     *
     * @param repository
     *
     * @return instance of view model.
     */
    @FeatureScope
    @Provides
    fun providesRepoDetailViewModel(
        repository: GithubRepoRepository,
    ) = fragment.viewModel {
        RepoDetailViewModel(
            repository = repository,
        )
    }
}