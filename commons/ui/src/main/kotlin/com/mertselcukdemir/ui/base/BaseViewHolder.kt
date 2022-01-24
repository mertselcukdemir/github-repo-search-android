package com.mertselcukdemir.ui.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by mertselcukdemir on 21.01.2022
 * All rights reserved.
 */

/**
 * Base view holder to standardize and simplify initialization for this component.
 *
 * @param binding View data binding generated class instance.
 * @see RecyclerView.ViewHolder
 */
abstract class BaseViewHolder<T : ViewDataBinding>(
    val binding: T
) : RecyclerView.ViewHolder(binding.root)