package com.mertselcukdemir.core.data.repositories

import com.mertselcukdemir.core.data.RepositoryListResponse
import com.mertselcukdemir.core.network.services.GithubRepoService
import retrofit2.Response

/**
 * Created by mertselcukdemir on 20.01.2022
 * All rights reserved.
 */

private const val SORT_KEY = "sort"
private const val SORT_VAL = "stars"
private const val ORDER_KEY = "order"
private const val ORDER_VAL = "desc"
private const val KEYWORD_KEY = "q"

/**
 * Repository module for handling Github API network operations [GithubRepoService].
 */
class GithubRepoRepository(private val service: GithubRepoService) {
    var keyword: String = "Xapo"

    /**
     * Get filtered repositories by given keyword.
     *
     * * @param type  for the specific item you want to find. It is "repository" for this project.
     */
    suspend fun getRepoList(type: String, queries: HashMap<String, String?>): Response<RepositoryListResponse> {
        return service.getRepositories(type, queries)
    }

    fun getQueries(): HashMap<String, String?> {
        return hashMapOf(
            SORT_KEY to SORT_KEY,
            ORDER_KEY to ORDER_VAL,
            KEYWORD_KEY to keyword
        )
    }

    fun initKeyword(keyword: String) {
        this.keyword = keyword
    }
}