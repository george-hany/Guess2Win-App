package com.feature.leagues.ui.leaguesRank

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import com.core.base.BaseFragment
import com.feature.leagues.BR
import com.feature.leagues.R
import com.feature.leagues.databinding.FragmentLeaguesRankBinding
import com.feature.leagues.ui.leaguesRank.adapter.RankListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.core.utils.SpacingItemDecoration

/**
 * A simple [Fragment] subclass.
 */
class LeaguesRankFragment : BaseFragment<FragmentLeaguesRankBinding, LeaguesRankViewModel>() {
    val leaguesRankViewModel: LeaguesRankViewModel by viewModel()
    var opend = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isSubFragment = true
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser && !opend) {
            leaguesRankViewModel.getRanks(arguments?.getString(LEAGUE_ID) ?: "")
            opend = true
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRanksRecyclerEnvironment()
        leaguesRankMediatorLiveDataObserver()
        ranksUIListLiveDataObserver()
        swipeRefreshLayoutListener()
    }

    private fun swipeRefreshLayoutListener() {
        viewDataBinding.swipeRefreshLayout.setOnRefreshListener {
            leaguesRankViewModel.getRanks(
                arguments?.getString(
                    LEAGUE_ID
                ) ?: ""
            )
        }
    }

    private fun ranksUIListLiveDataObserver() {
        viewDataBinding.swipeRefreshLayout.isRefreshing = true
        leaguesRankViewModel.ranksUIListLiveData.observe(viewLifecycleOwner, Observer {
            viewDataBinding.adapter?.run {
                ranksList.clear()
                ranksList.addAll(it)
                notifyDataSetChanged()
            }
            viewDataBinding.swipeRefreshLayout.isRefreshing = false
        })
    }

    private fun leaguesRankMediatorLiveDataObserver() {
        leaguesRankViewModel.leaguesRankMediatorLiveData.observe(viewLifecycleOwner, Observer {})
    }

    private fun setupRanksRecyclerEnvironment() {
        viewDataBinding.run {
            adapter = RankListAdapter(arrayListOf(), leaguesRankViewModel.userId)
            rankRecycler.addItemDecoration(SpacingItemDecoration(0, 0, 20, 20))
        }
    }

    override fun bindingVariable(): Int = BR.viewModel

    override fun layoutId(): Int = R.layout.fragment_leagues_rank

    override fun getViewModel(): LeaguesRankViewModel = leaguesRankViewModel

    companion object {
        fun newInstance(leagueId: String): LeaguesRankFragment = LeaguesRankFragment().apply {
            arguments = Bundle().apply {
                putString(LEAGUE_ID, leagueId)
            }
        }

        val LEAGUE_ID = "LEAGUE_ID"
    }

    override fun handleError() {}
}
