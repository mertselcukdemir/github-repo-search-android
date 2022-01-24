package com.mertselcukdemir.repo.ui.detail.di

import com.mertselcukdemir.core.di.CoreComponent
import com.mertselcukdemir.core.di.scopes.FeatureScope
import com.mertselcukdemir.repo.ui.detail.RepoDetailFragment
import dagger.Component

/**
 * Created by mertselcukdemir on 24.01.2022
 * All rights reserved.
 */

/**
 * Class for which a fully-formed, dependency-injected implementation is to
 * be generated from [RepoDetailModule].
 *
 * @see Component
 */

@FeatureScope
@Component(
    modules = [RepoDetailModule::class],
    dependencies = [CoreComponent::class]
)
interface RepoDetailComponent {

    /**
     * Inject dependencies on component.
     *
     * @param detailFragment Detail component.
     */
    fun inject(detailFragment: RepoDetailFragment)
}