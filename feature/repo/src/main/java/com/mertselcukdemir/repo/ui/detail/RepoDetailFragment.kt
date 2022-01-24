package com.mertselcukdemir.repo.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mertselcukdemir.githubrepo.GithubRepoApplication.Companion.coreComponent
import com.mertselcukdemir.repo.R
import com.mertselcukdemir.repo.databinding.FragmentRepoDetailBinding
import com.mertselcukdemir.repo.ui.detail.di.DaggerRepoDetailComponent
import com.mertselcukdemir.repo.ui.detail.di.RepoDetailModule
import com.mertselcukdemir.ui.base.BaseFragment
import com.mertselcukdemir.ui.extensions.observe

/**
 * Created by mertselcukdemir on 24.01.2022
 * All rights reserved.
 */

/**
 * View detail for selected Github repository
 *
 * @see BaseFragment
 */
class RepoDetailFragment : BaseFragment<FragmentRepoDetailBinding, RepoDetailViewModel>(
    layoutId = R.layout.fragment_repo_detail
) {

    private val args: RepoDetailFragmentArgs by navArgs()

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
        observe(viewModel.errorMessage, ::onErrorInitialized)
        viewModel.loadRepoDetail(args.owner, args.repo)
    }

    /**
     * Initialize dagger injection dependency graph.
     */
    override fun onInitDependencyInjection() {
        DaggerRepoDetailComponent
            .builder()
            .coreComponent(coreComponent(requireContext()))
            .repoDetailModule(RepoDetailModule(this))
            .build()
            .inject(this)
    }

    /**
     * Initialize view data binding variables.
     */
    override fun onInitDataBinding() {
        viewBinding.viewModel = viewModel
        viewBinding.fragment = this
    }

    // ============================================================================================
    //  View click methods
    // ============================================================================================

    /**
     * Dismiss the detail view.
     */
    fun onBackPressed(@Suppress("UNUSED_PARAMETER") view: View) {
        findNavController().navigateUp()
    }

    /**
     *  Display the website of the repository  in a web browser
     */
    fun showRepo(@Suppress("UNUSED_PARAMETER") view: View) {
        viewModel.data.value?.htmlUrl.let { url ->
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        }
    }
}