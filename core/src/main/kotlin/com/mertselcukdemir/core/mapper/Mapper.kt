package com.mertselcukdemir.core.mapper

/**
 * Created by mertselcukdemir on 20.01.2022
 * All rights reserved.
 */

/**
 * Helper class to transforms a specific input to desired object output, implementing for that
 * all operations required to transform.
 */
interface Mapper<F, T> {
    /**
     * Mapping object.
     *
     * @param from Initial object to from mapping.
     * @return An Object containing the results of applying the transformation.
     */
    suspend fun map(from: F): T
}