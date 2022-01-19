package com.mertselcukdemir.core.network

/**
 * Created by mertselcukdemir on 20.01.2022
 * All rights reserved.
 */

/**
 * Different states for any network request.
 */
sealed class NetworkState {

    /**
     * Success network state.
     *
     * @param isAdditional Is additional request.
     * @param isEmptyResponse Is the body of response empty.
     */
    data class Success(
        val isAdditional: Boolean = false,
        val isEmptyResponse: Boolean = false
    ) : NetworkState()

    /**
     * Loading network state.
     *
     * @param isAdditional Is additional request.
     */
    data class Loading(
        val isAdditional: Boolean = false
    ) : NetworkState()

    /**
     * Error network state.
     *
     * @param isAdditional Is additional request.
     */
    data class Error(
        val isAdditional: Boolean = false
    ) : NetworkState()

    // ============================================================================================
    //  Public helpers methods
    // ============================================================================================

    /**
     * Check if current network state is [Success].
     *
     * @return True if is success state, otherwise false.
     */
    fun isSuccess() = this is Success

    /**
     * Check if current network state is [Loading].
     *
     * @return True if is loading state, otherwise false.
     */
    fun isLoading() = this is Loading

    /**
     * Check if current network state is [Error].
     *
     * @return True if is error state, otherwise false.
     */
    fun isError() = this is Error
}