package com.mertselcukdemir.core.network.services

import com.mertselcukdemir.core.data.RepositoryListResponse
import com.mertselcukdemir.core.data.RepositoryModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

/**
 * Created by mertselcukdemir on 20.01.2022
 * All rights reserved.
 */

interface GithubRepoService {
    /**
     * Fetches lists of repositories with given filters.
     *
     * @param type  for the specific item you want to find. It is repository for this project.
     * @param queries A HashMap that contains required queries like page, sort, etc.
     * @return Response of filtered repositories.
     */
    @GET("search/{type}")
    suspend fun getRepositories(
        @Path("type") type: String,
        @QueryMap queries: Map<String, String?>
    ): Response<RepositoryListResponse>


    /**
     * Fetches requested single repo.
     *
     * @param owner Repository owner.
     * @param repo Name of the repository.
     * @return Response for single repo resource.
     */
    @GET("repos/{owner}/{repo}")
    suspend fun getRepoDetail(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
    ): Response<RepositoryModel>
}