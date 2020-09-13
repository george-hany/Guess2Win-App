package com.feature.rank.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.core.base.BaseFragment
import com.feature.rank.BR
import com.feature.rank.R
import com.feature.rank.databinding.FragmentRankContainerBinding
import com.feature.rank.ui.rankContainer.RankContainerViewModel
import com.feature.rank.ui.rankContainer.adapter.SectionsPagerAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class RankContainerFragment : BaseFragment<FragmentRankContainerBinding, RankContainerViewModel>() {
val rankContainerViewModel: RankContainerViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isSubFragment = true
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sectionsPagerAdapter =
            activity?.supportFragmentManager?.let { SectionsPagerAdapter(requireContext(), it) }
        viewDataBinding.run {
            viewPager.adapter = sectionsPagerAdapter
            tabs.setupWithViewPager(viewPager)
        }
    }
    override fun bindingVariable(): Int = BR.viewModel

    override fun layoutId(): Int = R.layout.fragment_rank_container

    override fun getViewModel(): RankContainerViewModel = rankContainerViewModel

    override fun handleError() {}
}
