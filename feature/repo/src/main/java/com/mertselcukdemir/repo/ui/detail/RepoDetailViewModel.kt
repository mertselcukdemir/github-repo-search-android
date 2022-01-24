package com.mertselcukdemir.repo.ui.detail

import androidx.lifecycle.ViewModel
import com.mertselcukdemir.core.data.repositories.GithubRepoRepository
import javax.inject.Inject

/**
 * Created by mertselcukdemir on 24.01.2022
 * All rights reserved.
 */

/**
 * View model responsible for preparing and managing the data for [RepoDetailFragment].
 *
 * @see ViewModel
 */
class RepoDetailViewModel @Inject constructor(
    val repository: GithubRepoRepository
) : ViewModel()