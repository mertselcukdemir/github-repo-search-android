package com.mertselcukdemir.repo.ui.list.adapter

import com.mertselcukdemir.ui.base.BaseViewState

/**
 * Created by mertselcukdemir on 23.01.2022
 * All rights reserved.
 */

/**
 * Different states for [RepoListAdapter].
 *
 * @see BaseViewState
 */
sealed class RepoListAdapterState (
    val hasExtraRow: Boolean
) {

    /**
     * Listed the added repos into list.
     */
    object Added : RepoListAdapterState(hasExtraRow = true)

    /**
     * Loading for new repos to add into list.
     */
    object AddLoading : RepoListAdapterState(hasExtraRow = true)

    /**
     * Error on add new repos into list.
     */
    object AddError : RepoListAdapterState(hasExtraRow = true)

    /**
     * No more repos to add into list.
     */
    object NoMore : RepoListAdapterState(hasExtraRow = false)

    // ============================================================================================
    //  Public helpers methods
    // ============================================================================================

    /**
     * Check if current view state is [Added].
     *
     * @return True if is added state, otherwise false.
     */
    fun isAdded() = this is Added

    /**
     * Check if current view state is [AddLoading].
     *
     * @return True if is add loading state, otherwise false.
     */
    fun isAddLoading() = this is AddLoading

    /**
     * Check if current view state is [AddError].
     *
     * @return True if is add error state, otherwise false.
     */
    fun isAddError() = this is AddError

    /**
     * Check if current view state is [NoMore].
     *
     * @return True if is no more elements state, otherwise false.
     */
    fun isNoMore() = this is NoMore
}
