package com.mertselcukdemir.repo.ui.list.di

import androidx.lifecycle.viewModelScope
import com.mertselcukdemir.core.data.repositories.GithubRepoRepository
import com.mertselcukdemir.core.di.scopes.FeatureScope
import com.mertselcukdemir.repo.ui.list.RepoListFragment
import com.mertselcukdemir.repo.ui.list.RepoListViewModel
import com.mertselcukdemir.repo.ui.list.paging.RepoPageDataSource
import com.mertselcukdemir.repo.ui.list.paging.RepoPageDataSourceFactory
import com.mertselcukdemir.ui.extensions.viewModel
import dagger.Module
import dagger.Provides

/**
 * Created by mertselcukdemir on 20.01.2022
 * All rights reserved.
 */

/**
 * Class that contributes to the object graph [RepoListComponent].
 *
 * @see Module
 */
@Module
class RepoListModule(private val fragment: RepoListFragment) {
    /**
     * Create a provider method binding for [RepoListViewModel].
     *
     * @param dataFactory Data source factory for repositories.
     * @return Instance of view model.
     * @see Provides
     */
    @FeatureScope
    @Provides
    fun providesRepoListViewModel(
        dataFactory: RepoPageDataSourceFactory
    ) = fragment.viewModel {
        RepoListViewModel(dataFactory)
    }

    /**
     * Create a provider method binding for [RepoPageDataSource].
     *
     * @return Instance of data source.
     * @see Provides
     */
    @Provides
    fun providesReposPageDataSource(
        viewModel: RepoListViewModel,
        repository: GithubRepoRepository,
    ) = RepoPageDataSource(
        repository = repository,
        scope = viewModel.viewModelScope,
    )
}