package com.mertselcukdemir.core.data

import com.squareup.moshi.Json

data class RepositoryListResponse(

    @Json(name = "total_count")
    val totalCount: Int? = null,

    @Json(name = "incomplete_results")
    val incompleteResults: Boolean? = null,

    @Json(name = "items")
    val items: List<RepositoryModel>? = null,

    @Json(name = "message")
    val message: String? = null
)