package com.mertselcukdemir.repo.ui.list

import com.mertselcukdemir.githubrepo.GithubRepoApplication.Companion.coreComponent
import com.mertselcukdemir.repo.R
import com.mertselcukdemir.repo.databinding.FragmentRepoListBinding
import com.mertselcukdemir.repo.ui.list.di.DaggerRepoListComponent
import com.mertselcukdemir.repo.ui.list.di.RepoListModule
import com.mertselcukdemir.ui.base.BaseFragment

/**
 * Created by mertselcukdemir on 20.01.2022
 * All rights reserved.
 */


class RepoListFragment : BaseFragment<FragmentRepoListBinding, RepoListViewModel>(
    layoutId = R.layout.fragment_repo_list
) {
    override fun onInitDependencyInjection() {
        DaggerRepoListComponent
            .builder()
            .coreComponent(coreComponent(requireContext()))
            .repoListModule(RepoListModule(this))
            .build()
            .inject(this)
    }

    override fun onInitDataBinding() {

    }

}