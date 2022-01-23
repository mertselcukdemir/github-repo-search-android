package com.mertselcukdemir.ui.utils

import androidx.appcompat.widget.SearchView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by mertselcukdemir on 21.01.2022
 * All rights reserved.
 */


/**
 * TextListener to observe query text that is changed by the user.
 *
 * @see SearchView.OnQueryTextListener
 */
class DebouncingQueryTextListener(
    lifecycleOwner: LifecycleOwner,
    private val onDebouncingQueryTextChange: (String?) -> Unit
) : SearchView.OnQueryTextListener {
    var debouncePeriod: Long = 500

    private val coroutineScope = lifecycleOwner.lifecycleScope

    private var searchJob: Job? = null

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        searchJob?.cancel()
        searchJob = coroutineScope.launch {
            newText?.let {
                delay(debouncePeriod)
                onDebouncingQueryTextChange(newText)
            }
        }
        return false
    }
}