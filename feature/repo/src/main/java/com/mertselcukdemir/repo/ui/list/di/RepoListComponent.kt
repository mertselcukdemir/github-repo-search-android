package com.mertselcukdemir.repo.ui.list.di

import com.mertselcukdemir.core.di.CoreComponent
import com.mertselcukdemir.core.di.scopes.FeatureScope
import com.mertselcukdemir.repo.ui.list.RepoListFragment
import dagger.Component

/**
 * Created by mertselcukdemir on 20.01.2022
 * All rights reserved.
 */

/**
 * Class for which a fully-formed, dependency-injected implementation is to
 * be generated from [RepoListModule].
 *
 * @see Component
 */
@FeatureScope
@Component(
    modules = [RepoListModule::class],
    dependencies = [CoreComponent::class]
)
interface RepoListComponent {
    /**
     * Inject dependencies on component.
     *
     * @param listFragment Repo list component.
     */
    fun inject(listFragment: RepoListFragment)
}