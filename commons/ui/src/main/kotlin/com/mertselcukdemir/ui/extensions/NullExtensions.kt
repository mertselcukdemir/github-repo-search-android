package com.mertselcukdemir.ui.extensions

/**
 * Created by mertselcukdemir on 25.01.2022
 * All rights reserved.
 */

inline fun <A, B, R> ifNotNull(a: A?, b: B?, code: (A, B) -> R) {
    if (a != null && b != null) {
        code(a, b)
    }
}
