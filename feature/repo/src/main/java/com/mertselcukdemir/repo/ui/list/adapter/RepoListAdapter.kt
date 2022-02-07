package com.mertselcukdemir.repo.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mertselcukdemir.core.data.RepositoryModel
import com.mertselcukdemir.repo.ui.list.adapter.holders.RepoListViewHolder
import com.mertselcukdemir.ui.base.BasePagedListAdapter

/**
 * Created by mertselcukdemir on 21.01.2022
 * All rights reserved.
 */

/**
 * Class for presenting repos List data in a [RecyclerView], including computing
 * diffs between Lists on a background thread.
 *
 * @see BasePagedListAdapter
 */
class RepoListAdapter(
    private val repoCallBack: (model: RepositoryModel) -> Unit,
    private val retryCallBack: () -> Unit,
) : BasePagedListAdapter<RepositoryModel>(
    itemsSame = { old, new -> old.id == new.id },
    contentsSame = { old, new -> old == new }
) {

    /**
     * Called when RecyclerView needs a new [RecyclerView.ViewHolder] of the given type to
     * represent an item.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     * an adapter position.
     * @param inflater Instantiates a layout XML file into its corresponding View objects.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return RepoListViewHolder(inflater)
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let {
            if (holder is RepoListViewHolder) {
                holder.bind(it, repoCallBack)
            }
        }
    }
}