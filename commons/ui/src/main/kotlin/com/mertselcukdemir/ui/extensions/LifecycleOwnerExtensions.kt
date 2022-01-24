package com.mertselcukdemir.ui.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * Created by mertselcukdemir on 21.01.2022
 * All rights reserved.
 */

/**
 * Adds the given observer to the observers list within the lifespan of the given
 * owner. The events are dispatched on the main thread. If LiveData already has data
 * set, it will be delivered to the observer.
 *
 * @param liveData The liveData to observe.
 * @param observer The observer that will receive the events.
 * @see LiveData.observe
 */
fun <T> LifecycleOwner.observe(liveData: LiveData<T>, observer: (T) -> Unit) {
    liveData.observe(
        this,
        {
            it?.let { t -> observer(t) }
        }
    )
}

/**
 * Adds the given observer to the observers list within the lifespan of the given
 * owner. The events are dispatched on the main thread. If LiveData already has data
 * set, it will be delivered to the observer.
 *
 * @param liveData The mutableLiveData to observe.
 * @param observer The observer that will receive the events.
 * @see MutableLiveData.observe
 */
fun <T> LifecycleOwner.observe(liveData: MutableLiveData<T>, observer: (T) -> Unit) {
    liveData.observe(
        this,
        {
            it?.let { t -> observer(t) }
        }
    )
}
