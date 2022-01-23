package com.mertselcukdemir.core.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by mertselcukdemir on 24.01.2022
 * All rights reserved.
 */

@JsonClass(generateAdapter = true)
data class ErrorResponse(
    @Json(name = "message")
    val message: String
)
