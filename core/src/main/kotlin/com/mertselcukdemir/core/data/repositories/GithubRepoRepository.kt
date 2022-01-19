package com.mertselcukdemir.core.data.repositories

import com.mertselcukdemir.core.network.services.GithubRepoService
import javax.inject.Inject

/**
 * Created by mertselcukdemir on 20.01.2022
 * All rights reserved.
 */

/**
 * Repository module for handling Github API network operations [GithubRepoService].
 */
class GithubRepoRepository @Inject constructor (private val service: GithubRepoService) {
}