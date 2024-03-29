package com.mertselcukdemir.ui.bindings

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

/**
 * Created by mertselcukdemir on 21.01.2022
 * All rights reserved.
 */

/**
 * Set image loaded from url.
 *
 * @param url Image url to download and set as drawable.
 */
@BindingAdapter("imageUrl")
fun ImageView.imageUrl(url: String?) {
    load(url)
}


@BindingAdapter("goneUnless")
fun goneUnless(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}