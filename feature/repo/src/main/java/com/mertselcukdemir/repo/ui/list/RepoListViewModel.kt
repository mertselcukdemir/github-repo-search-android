package com.mertselcukdemir.repo.ui.list

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import com.mertselcukdemir.core.network.NetworkState
import com.mertselcukdemir.repo.ui.list.paging.PAGE_MAX_ELEMENTS
import com.mertselcukdemir.repo.ui.list.paging.RepoPageDataSourceFactory
import com.mertselcukdemir.ui.base.BaseViewModel
import javax.inject.Inject

/**
 * Created by mertselcukdemir on 20.01.2022
 * All rights reserved.
 */

private const val KEYWORD_SAVE_KEY = "KEYWORD_SAVE_KEY"

/**
 * View model responsible for preparing and managing the data for [RepoListFragment].
 *
 * @see ViewModel
 */
class RepoListViewModel @Inject constructor(
    private val dataSourceFactory: RepoPageDataSourceFactory
) : BaseViewModel() {
    private val networkState = Transformations.switchMap(dataSourceFactory.sourceLiveData) {
        it.networkState
    }
    val data = LivePagedListBuilder(dataSourceFactory, PAGE_MAX_ELEMENTS).build()
    val isEmptyResponse: MutableLiveData<Boolean> = MutableLiveData(false)
    val keyword: MutableLiveData<String> = MutableLiveData()
    val state = Transformations.map(networkState) {
        when (it) {
            is NetworkState.Success -> {
                loading.value = false
                if (it.isAdditional && it.isEmptyResponse) {
                    RepoListViewState.NoMoreElements
                } else if (it.isEmptyResponse) {
                    isEmptyResponse.value = true
                    RepoListViewState.Empty
                } else {
                    RepoListViewState.Loaded
                }
            }

            is NetworkState.Loading -> {
                isEmptyResponse.value = false
                if (it.isAdditional) {
                    RepoListViewState.AddLoading
                } else {
                    loading.value = true
                    RepoListViewState.Loading
                }
            }

            is NetworkState.Error -> {
                loading.value = false
                isEmptyResponse.value = false
                if (it.isAdditional) {
                    handleError(it.errorData)
                    RepoListViewState.AddError
                } else {
                    handleError(it.errorData)
                    RepoListViewState.Error
                }
            }
        }
    }

    fun search(keyword: String) {
        dataSourceFactory.search(keyword)
    }

    /**
     * Retry last fetch operation to add repos into list.
     */
    fun retryAddReposList() {
        dataSourceFactory.retry()
    }

    /**
     * Save the current instance state of the given Fragment.
     * @param outState Bundle to save state of [keyword]
     */
    fun saveToBundle(outState: Bundle) {
        keyword.value?.let {
            outState.putString(KEYWORD_SAVE_KEY, it)
        }
    }

    /**
     * Restore save data when fragment re-initialized
     * @param savedInstanceState will restore the saved [keyword]
     */
    fun restoreFromBundle(savedInstanceState: Bundle?) {
        if (keyword.value == null) {
            savedInstanceState?.getString(KEYWORD_SAVE_KEY)?.let {
                keyword.value = it
            }
        }
    }
}
