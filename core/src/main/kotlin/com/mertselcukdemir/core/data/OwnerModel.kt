package com.mertselcukdemir.core.data

import com.squareup.moshi.Json

data class OwnerModel(

    @Json(name = "avatar_url")
    val avatarUrl: String? = null,

    @Json(name = "html_url")
    val htmlUrl: String? = null,

    @Json(name = "id")
    val id: Int? = null,

    @Json(name = "login")
    val login: String? = null,

    @Json(name = "url")
    val url: String? = null
)