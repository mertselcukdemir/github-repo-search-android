package com.mertselcukdemir.ui.extensions

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.fragment.findNavController
import com.mertselcukdemir.ui.R
import timber.log.Timber

/**
 * Created by mertselcukdemir on 24.01.2022
 * All rights reserved.
 */

/**
 * Navigates only if this is safely possible; when this Fragment is still the current destination.
 */
fun Fragment.navigateSafe(
    @IdRes resId: Int,
    args: Bundle? = null,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null
) {
    if (mayNavigate()) findNavController().navigate(
        resId, args,
        navOptions, navigatorExtras
    )
}


/**
 * Returns true if the navigation controller is still pointing at 'this' fragment, or false if it already navigated away.
 */
fun Fragment.mayNavigate(): Boolean {

    try {
        val navController = findNavController()
        val destinationIdInNavController = navController.currentDestination?.id

        // add tag_navigation_destination_id to your ids.xml so that it's unique:
        val destinationIdOfThisFragment =
            view?.getTag(R.id.tag_navigation_destination_id) ?: destinationIdInNavController

        // check that the navigation graph is still in 'this' fragment, if not then the app already navigated:
        return if (destinationIdInNavController == destinationIdOfThisFragment) {
            view?.setTag(R.id.tag_navigation_destination_id, destinationIdOfThisFragment)
            true
        } else {
            Timber.d("May not navigate: current destination is not the current fragment.")
            false
        }
    } catch (e: Exception) {
        return false
    }
}