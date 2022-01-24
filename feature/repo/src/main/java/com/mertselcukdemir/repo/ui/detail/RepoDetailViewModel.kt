package com.mertselcukdemir.repo.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertselcukdemir.core.data.RepositoryModel
import com.mertselcukdemir.core.data.repositories.GithubRepoRepository
import com.mertselcukdemir.ui.base.BaseViewModel
import com.mertselcukdemir.ui.extensions.ifNotNull
import kotlinx.coroutines.launch
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
) : BaseViewModel() {

    private val _data = MutableLiveData<RepositoryModel>()
    val data: LiveData<RepositoryModel>
        get() = _data

    /**
     * Fetch selected repo detail info.
     *
     * @param owner Repository owner
     * @param repo Name of the repository
     */
    fun loadRepoDetail(owner: String?, repo: String?) {
        viewModelScope.launch {
            ifNotNull(owner, repo) { repoOwner, repoName ->
                try {
                    loading.postValue(true)
                    val response = repository.getRepoDetail(repoOwner, repoName)
                    if (response.code() == 200 && response.body() is RepositoryModel) {
                        _data.postValue(response.body())
                    } else errorMessage.postValue(response.body()?.message)
                    loading.postValue(false)
                } catch (e: Exception) {
                    errorMessage.postValue(e.message.toString())
                }
            }
        }
    }
}