@file:JvmName("ActivityDataBinding")

package com.mertselcukdemir.ui.extensions

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


/**
 * This extension:
 * Automatically calls setLifecycleOwner.
 * Provides the unified way of declaring the binding variable in Activity and Fragment.
 *DataBinding-ktx is saving memory because of cleaning up the binding variable having the view tree after onDestroyView.
 *This needs one of the following calling inflater.inflate in onCreateView of Fragment.
 */
fun <T : ViewDataBinding> Fragment.dataBinding(): ReadOnlyProperty<Fragment, T> {
    return object : ReadOnlyProperty<Fragment, T> {
        @Suppress("UNCHECKED_CAST")
        override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
            (requireView().getTag(property.name.hashCode()) as? T)?.let { return it }
            return bind<T>(requireView()).also {
                it.lifecycleOwner = thisRef.viewLifecycleOwner
                it.root.setTag(property.name.hashCode(), it)
            }
        }

        private fun <T : ViewDataBinding> bind(view: View): T = DataBindingUtil.bind(view)!!
    }
}