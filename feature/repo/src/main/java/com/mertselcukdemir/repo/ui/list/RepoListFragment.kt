package com.mertselcukdemir.repo.ui.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import com.mertselcukdemir.core.data.RepositoryModel
import com.mertselcukdemir.githubrepo.GithubRepoApplication.Companion.coreComponent
import com.mertselcukdemir.repo.R
import com.mertselcukdemir.repo.databinding.FragmentRepoListBinding
import com.mertselcukdemir.repo.ui.list.adapter.RepoListAdapter
import com.mertselcukdemir.repo.ui.list.di.DaggerRepoListComponent
import com.mertselcukdemir.repo.ui.list.di.RepoListModule
import com.mertselcukdemir.ui.base.BaseFragment
import com.mertselcukdemir.ui.base.BaseViewModel
import com.mertselcukdemir.ui.extensions.observe
import com.mertselcukdemir.ui.utils.DebouncingQueryTextListener

/**
 * Created by mertselcukdemir on 20.01.2022
 * All rights reserved.
 */

/**
 * View listing the all Github Repositories with option to filter by keyword.
 *
 * @see BaseFragment
 */
class RepoListFragment : BaseFragment<FragmentRepoListBinding, RepoListViewModel>(
    layoutId = R.layout.fragment_repo_list
) {


    private var viewAdapter: RepoListAdapter? = null

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
        observe(viewModel.keyword, ::onKeywordChanged)
        observe(viewModel.errorMessage, ::onErrorInitialized)

        if (savedInstanceState != null) {
            viewModel.restoreFromBundle(savedInstanceState)
        }
    }

    override fun onInitDependencyInjection() {
        DaggerRepoListComponent
            .builder()
            .coreComponent(coreComponent(requireContext()))
            .repoListModule(RepoListModule(this))
            .build()
            .inject(this)
    }

    /**
     * Initialize view data binding variables.
     */
    override fun onInitDataBinding() {
        viewBinding.viewModel = viewModel
        startListeningQuery()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        viewBinding.searchRecycler.apply {
            layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
            //layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            viewAdapter = RepoListAdapter({ selectedRepo -> //selected item call back
                openRepoDetail(selectedRepo)
            }, { //retry callback
                viewModel.retryAddReposList()
            })
            adapter = viewAdapter
            //(layoutManager as GridLayoutManager).spanSizeLookup = viewAdapter!!.getSpanSizeLookup()
        }
    }

    /**
     * Send interaction event for open repo detail view from selected repo.
     *
     * @param selectedRepo Repository model that has been selected.
     */
    private fun openRepoDetail(selectedRepo: RepositoryModel) {
        findNavController().navigate(
            RepoListFragmentDirections.actionRepoListFragmentToRepoDetailFragment(
                selectedRepo.owner?.login,
                selectedRepo.name
            )
        )
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
                        viewModel.keyword.value = newText
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
        viewAdapter?.submitList(viewData)
        if (viewData.isNullOrEmpty().not()) {
            viewBinding.searchRecycler.apply {
                smoothScrollToPosition(0)
                layoutManager?.scrollToPosition(0)
            }
        }
    }

    /**
     * Observer view state change on [RepoListViewModel].
     *
     * Ignored for this demonstration. But mostly useful for analytics events
     * @param viewState State of repos list.
     */
    private fun onViewStateChange(viewState: RepoListViewState) {
        when (viewState) {
            is RepoListViewState.Loaded -> {}
            is RepoListViewState.AddLoading -> {}
            is RepoListViewState.AddError -> {}
            is RepoListViewState.NoMoreElements -> {}
            else -> {}
        }
    }

    private fun onKeywordChanged(keyword: String) {
        viewModel.search(keyword)
    }

    /**
     *Called to ask the fragment to save its current dynamic state,
     * so it can later be reconstructed in a new instance if its process is restarted
     *
     * @see Fragment.onSaveInstanceState
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel.saveToBundle(outState)
    }
}