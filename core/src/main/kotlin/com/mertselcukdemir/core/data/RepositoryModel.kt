package com.mertselcukdemir.core.data

import com.squareup.moshi.Json

data class RepositoryModel(

    @Json(name = "owner")
    val owner: OwnerModel? = null,

    @Json(name = "visibility")
    val visibility: String? = null,

    @Json(name = "description")
    val description: String? = null,

    @Json(name = "created_at")
    val createdAt: String? = null,

    @Json(name = "url")
    val url: String? = null,

    @Json(name = "full_name")
    val fullName: String? = null,

    @Json(name = "updated_at")
    val updatedAt: String? = null,

    @Json(name = "html_url")
    val htmlUrl: String? = null,

    @Json(name = "clone_url")
    val cloneUrl: String? = null,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "default_branch")
    val defaultBranch: String? = null,

    @Json(name = "id")
    val id: Long? = null,

    @Json(name = "language")
    val language: String? = null,

    @Json(name = "stargazers_count")
    val stargazers_count: Long? = null,

    @Json(name = "git_url")
    val gitUrl: String? = null,

    @Json(name = "node_id")
    val nodeId: String? = null,

    @Json(name = "homepage")
    val homepage: String? = null,

    @Json(name = "message")
    val message: String? = null
)