package com.mertselcukdemir.repo.ui.list

import com.mertselcukdemir.ui.base.BaseViewState

/**
 * Created by mertselcukdemir on 21.01.2022
 * All rights reserved.
 */

/**
 * Different states for [RepoListViewState].
 *
 * @see BaseViewState
 */
sealed class RepoListViewState : BaseViewState {
    /**
     * Refreshing repo list.
     */
    object Refreshing : RepoListViewState()

    /**
     * Loaded repo list.
     */
    object Loaded : RepoListViewState()

    /**
     * Loading repo list.
     */
    object Loading : RepoListViewState()

    /**
     * Loading on add more elements into repo list.
     */
    object AddLoading : RepoListViewState()

    /**
     * Empty repo list.
     */
    object Empty : RepoListViewState()

    /**
     * Error on loading repo list.
     */
    object Error : RepoListViewState()

    /**
     * Error on add more elements into repo list.
     */
    object AddError : RepoListViewState()

    /**
     * No more elements for adding into repo list.
     */
    object NoMoreElements : RepoListViewState()

    // ============================================================================================
    //  Public helpers methods
    // ============================================================================================

    /**
     * Check if current view state is [Refreshing].
     *
     * @return True if is refreshing state, otherwise false.
     */
    fun isRefreshing() = this is Refreshing

    /**
     * Check if current view state is [Loaded].
     *
     * @return True if is loaded state, otherwise false.
     */
    fun isLoaded() = this is Loaded

    /**
     * Check if current view state is [Loading].
     *
     * @return True if is loading state, otherwise false.
     */
    fun isLoading() = this is Loading

    /**
     * Check if current view state is [AddLoading].
     *
     * @return True if is add loading state, otherwise false.
     */
    fun isAddLoading() = this is AddLoading

    /**
     * Check if current view state is [Empty].
     *
     * @return True if is empty state, otherwise false.
     */
    fun isEmpty() = this is Empty

    /**
     * Check if current view state is [Error].
     *
     * @return True if is error state, otherwise false.
     */
    fun isError() = this is Error

    /**
     * Check if current view state is [AddError].
     *
     * @return True if is add error state, otherwise false.
     */
    fun isAddError() = this is AddError

    /**
     * Check if current view state is [NoMoreElements].
     *
     * @return True if is no more elements state, otherwise false.
     */
    fun isNoMoreElements() = this is NoMoreElements

}