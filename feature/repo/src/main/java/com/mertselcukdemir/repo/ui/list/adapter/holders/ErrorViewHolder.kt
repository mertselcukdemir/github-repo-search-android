package com.mertselcukdemir.repo.ui.list.adapter.holders

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.mertselcukdemir.repo.databinding.ListItemErrorBinding
import com.mertselcukdemir.ui.base.BaseViewHolder

/**
 * Created by mertselcukdemir on 21.01.2022
 * All rights reserved.
 */

/**
 * Class describes repos error view and metadata about its place within the [RecyclerView].
 *
 * @see BaseViewHolder
 */
class ErrorViewHolder(
    inflater: LayoutInflater
) : BaseViewHolder<ListItemErrorBinding>(
    binding = ListItemErrorBinding.inflate(inflater)
) {

    /**
     * Bind view data binding variables.
     *
     * @param retryCallBack callback to retry fetching repos.
     */
    fun bind(retryCallBack: () -> Unit) {
        binding.executePendingBindings()
        binding.retryButton.setOnClickListener {
            retryCallBack.invoke()
        }
    }
}
