package com.mertselcukdemir.repo.ui.list.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.mertselcukdemir.core.data.ErrorData
import com.mertselcukdemir.core.data.RepositoryModel
import com.mertselcukdemir.core.data.repositories.GithubRepoRepository
import com.mertselcukdemir.core.network.NetworkState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import java.util.*
import javax.inject.Inject
import kotlin.collections.HashMap

/**
 * Created by mertselcukdemir on 21.01.2022
 * All rights reserved.
 */

private const val STARTING_PAGE_INDEX = 1
private const val PAGE_KEY = "page"
const val PAGE_MAX_ELEMENTS = 10
const val SEARCH_TYPE = "repositories"

/**
 * Incremental data loader for page-keyed content, where requests return keys for next/previous
 * pages. Obtaining paginated the trending repositories.
 *
 * @see PageKeyedDataSource
 */

class RepoPageDataSource @Inject constructor(
    val repository: GithubRepoRepository,
    val scope: CoroutineScope,
) : PageKeyedDataSource<Int, RepositoryModel>() {

    var queries: HashMap<String, String?> = HashMap()
    val networkState = MutableLiveData<NetworkState>()
    var retry: (() -> Unit)? = null

    /**
     * Load initial data.
     *
     * @param params Parameters for initial load, including requested load size.
     * @param callback Callback that receives initial load data.
     * @see PageKeyedDataSource.loadInitial
     */
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, RepositoryModel>) {
        networkState.postValue(NetworkState.Loading())
        scope.launch(
            CoroutineExceptionHandler { _, throwable ->
                retry = {
                    loadInitial(params, callback)
                }
                networkState.postValue(
                    NetworkState.Error(
                        false,
                        ErrorData(errorCode = null, errorMessage = throwable.message)
                    )
                )
            }
        ) {
            queries = repository.getQueries()
            queries[PAGE_KEY] = STARTING_PAGE_INDEX.toString()
            val response = repository.getRepoList(
                type = SEARCH_TYPE,
                queries = queries
            )
            when (response.code()) {
                200 -> {
                    val items = response.body()?.items
                    if (items.isNullOrEmpty().not()) {
                        callback.onResult(items!!, null, PAGE_MAX_ELEMENTS)
                        networkState.postValue(NetworkState.Success(isEmptyResponse = false))
                    }
                    else networkState.postValue(NetworkState.Success(isEmptyResponse = true))
                }
                else -> {
                    networkState.postValue(
                        NetworkState.Error(
                            true,
                            ErrorData(response.code(), (response.errorBody() as ResponseBody).string())
                        )
                    )
                }
            }
        }
    }

    /**
     * Append page with the key specified by [PageKeyedDataSource.LoadParams.key].
     *
     * @param params Parameters for the load, including the key for the new page, and requested
     * load size.
     * @param callback Callback that receives loaded data.
     * @see PageKeyedDataSource.loadAfter
     */
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, RepositoryModel>) {
        networkState.postValue(NetworkState.Loading(true))
        scope.launch(
            CoroutineExceptionHandler { _, throwable ->
                retry = {
                    loadAfter(params, callback)
                }
                networkState.postValue(
                    NetworkState.Error(
                        false,
                        ErrorData(errorCode = null, errorMessage = throwable.message)
                    )
                )
            }
        ) {
            delay(500)
            queries[PAGE_KEY] = ((params.key / 10) + 1).toString()
            val response = repository.getRepoList(
                type = SEARCH_TYPE,
                queries = queries
            )
            when (response.code()) {
                200 -> {
                    val items = response.body()?.items
                    if (items.isNullOrEmpty().not()) {
                        callback.onResult(items!!, params.key + PAGE_MAX_ELEMENTS)
                        networkState.postValue(NetworkState.Success(true, items.isEmpty()))
                    }
                }
                else -> {
                    networkState.postValue(
                        NetworkState.Error(
                            true,
                            ErrorData(response.code(), (response.errorBody() as ResponseBody).string())
                        )
                    )
                }
            }
        }
    }

    /**
     * Prepend page with the key specified by [LoadParams.key]
     *
     * @param params Parameters for the load, including the key for the new page, and requested
     * load size.
     * @param callback Callback that receives loaded data.
     * @see PageKeyedDataSource.loadBefore
     */
    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, RepositoryModel>) {
        // Ignored, since we only ever append to our initial load
    }

    /**
     * Force retry last fetch operation in case it has ever been previously executed.
     */
    fun retry() {
        retry?.invoke()
    }
}