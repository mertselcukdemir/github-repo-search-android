package com.mertselcukdemir.repo.ui.list

import android.os.Bundle
import android.view.View
import androidx.paging.PagedList
import com.mertselcukdemir.core.data.RepositoryModel
import com.mertselcukdemir.githubrepo.GithubRepoApplication.Companion.coreComponent
import com.mertselcukdemir.repo.R
import com.mertselcukdemir.repo.databinding.FragmentRepoListBinding
import com.mertselcukdemir.repo.ui.list.di.DaggerRepoListComponent
import com.mertselcukdemir.repo.ui.list.di.RepoListModule
import com.mertselcukdemir.ui.base.BaseFragment
import com.mertselcukdemir.ui.extensions.observe
import com.mertselcukdemir.ui.utils.DebouncingQueryTextListener

/**
 * Created by mertselcukdemir on 20.01.2022
 * All rights reserved.
 */


class RepoListFragment : BaseFragment<FragmentRepoListBinding, RepoListViewModel>(
    layoutId = R.layout.fragment_repo_list
) {

    /**
     * Called to have the fragment instantiate its user interface view.
     *
     * @param view The view returned by onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     * @see BaseFragment.onViewCreated
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe(viewModel.state, ::onViewStateChange)
        observe(viewModel.data, ::onViewDataChange)
    }

    override fun onInitDependencyInjection() {
        DaggerRepoListComponent
            .builder()
            .coreComponent(coreComponent(requireContext()))
            .repoListModule(RepoListModule(this))
            .build()
            .inject(this)
    }

    override fun onInitDataBinding() {
        viewBinding.viewModel = viewModel
        startListeningQuery()
    }

    /**
     * Observe input entered by user and send it to [RepoListViewModel] to return repositories
     *
     * * @see [DebouncingQueryTextListener]
     */
    private fun startListeningQuery() {
        viewBinding.searchView.setOnQueryTextListener(
            DebouncingQueryTextListener(
                this
            ) { newText ->
                newText?.let {
                    if (newText.length >= 3) {
                        viewModel.search(newText)
                    }
                }
            }
        )
    }


    /**
     * Observer view data change on [RepoListViewModel].
     *
     * @param viewData Paged list of repositories.
     */
    private fun onViewDataChange(viewData: PagedList<RepositoryModel>) {
        //viewAdapter.submitList(viewData)
    }


    /**
     * Observer view state change on [RepoListViewModel].
     *
     * @param viewState State of repo list.
     */
    private fun onViewStateChange(viewState: RepoListViewState) {
        when (viewState) {
            // TODO: 21.01.2022
        }
    }

}