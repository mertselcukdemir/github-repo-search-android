package com.mertselcukdemir.repo.ui.list

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import com.mertselcukdemir.core.network.NetworkState
import com.mertselcukdemir.repo.ui.list.paging.PAGE_MAX_ELEMENTS
import com.mertselcukdemir.repo.ui.list.paging.RepoPageDataSourceFactory
import javax.inject.Inject

/**
 * Created by mertselcukdemir on 20.01.2022
 * All rights reserved.
 */

/**
 * View model responsible for preparing and managing the data for [RepoListFragment].
 *
 * @see ViewModel
 */
class RepoListViewModel @Inject constructor(
    val dataSourceFactory: RepoPageDataSourceFactory
) : ViewModel() {
    val networkState = Transformations.switchMap(dataSourceFactory.sourceLiveData) {
        it.networkState
    }
    val data = LivePagedListBuilder(dataSourceFactory, PAGE_MAX_ELEMENTS).build()
    val state = Transformations.map(networkState) {
        when (it) {
            is NetworkState.Success ->
                if (it.isAdditional && it.isEmptyResponse) {
                    RepoListViewState.NoMoreElements
                } else if (it.isEmptyResponse) {
                    RepoListViewState.Empty
                } else {
                    RepoListViewState.Loaded
                }
            is NetworkState.Loading ->
                if (it.isAdditional) {
                    RepoListViewState.AddLoading
                } else {
                    RepoListViewState.Loading
                }
            is NetworkState.Error ->
                if (it.isAdditional) {
                    RepoListViewState.AddError
                } else {
                    RepoListViewState.Error
                }
        }
    }

    fun search(keyword: String) {
        dataSourceFactory.search(keyword)
    }

    fun clear() {
        data.value?.clear()
    }
}
