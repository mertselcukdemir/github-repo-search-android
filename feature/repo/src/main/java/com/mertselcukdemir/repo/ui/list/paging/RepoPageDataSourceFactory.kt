package com.mertselcukdemir.repo.ui.list.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.mertselcukdemir.core.data.RepositoryModel
import com.mertselcukdemir.core.data.repositories.GithubRepoRepository
import javax.inject.Inject
import javax.inject.Provider

/**
 * Created by mertselcukdemir on 21.01.2022
 * All rights reserved.
 */

/**
 * Data source factory which also provides a way to observe the last created data source.
 * This allows us to channel its network request status etc back to the UI.
 *
 * @see DataSource.Factory
 */
class RepoPageDataSourceFactory @Inject constructor(
    val providerDataSource: Provider<RepoPageDataSource>,
    val repo: GithubRepoRepository
) : DataSource.Factory<Int, RepositoryModel>() {

    /**
     * Create a DataSource.
     *
     * @return The new DataSource.
     * @see DataSource.Factory.create
     */
    var sourceLiveData = MutableLiveData<RepoPageDataSource>()
    override fun create(): DataSource<Int, RepositoryModel> {
        val dataSource = providerDataSource.get()
        sourceLiveData.postValue(dataSource)
        return dataSource
    }

    /**
     * Force refresh data source by invalidating and re-create again.
     */
    fun search(keyword: String) {
        repo.initKeyword(keyword)
        //sourceLiveData.value?.updateQueries(keyword)
        sourceLiveData.value?.invalidate()
    }


    /**
     * Force retry last fetch operation on data source.
     */
    fun retry() {
        sourceLiveData.value?.retry()
    }
}