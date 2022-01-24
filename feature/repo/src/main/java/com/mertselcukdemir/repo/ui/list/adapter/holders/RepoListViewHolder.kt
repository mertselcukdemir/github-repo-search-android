package com.mertselcukdemir.repo.ui.list.adapter.holders

import android.view.LayoutInflater
import com.mertselcukdemir.core.data.RepositoryModel
import com.mertselcukdemir.repo.databinding.AdapterRepoBinding
import com.mertselcukdemir.repo.ui.list.RepoListFragment
import com.mertselcukdemir.ui.base.BaseViewHolder

/**
 * Created by mertselcukdemir on 21.01.2022
 * All rights reserved.
 */

class RepoListViewHolder(
    inflater: LayoutInflater
) : BaseViewHolder<AdapterRepoBinding>(
    binding = AdapterRepoBinding.inflate(inflater)
) {

    /**
     * Bind view data binding variables.
     *
     * @param model Repo list item.
     * @param callBack callback that returns [RepositoryModel] to [RepoListFragment] for navigating to next fragment.
     */
    fun bind(model: RepositoryModel, callBack: (repo: RepositoryModel) -> Unit) {
        binding.model = model
        binding.root.setOnClickListener {
            callBack.invoke(model)
        }
        binding.executePendingBindings()
    }
}
