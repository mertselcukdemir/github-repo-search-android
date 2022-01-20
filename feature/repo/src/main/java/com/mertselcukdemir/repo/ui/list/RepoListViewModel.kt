package com.mertselcukdemir.repo.ui.list

import androidx.lifecycle.ViewModel
import com.mertselcukdemir.core.data.repositories.GithubRepoRepository
import javax.inject.Inject

/**
 * Created by mertselcukdemir on 20.01.2022
 * All rights reserved.
 */

/**
 * View model responsible for preparing and managing the data for [RepoListFragment].
 *
 * @see ViewModel
 */
class RepoListViewModel @Inject constructor(
    val repo: GithubRepoRepository
) : ViewModel() {
}