package com.feature.leagues.ui.leagues

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.core.base.BaseFragment
import com.feature.leagues.BR
import com.feature.leagues.R
import com.feature.leagues.databinding.FragmentLeaguesBinding
import com.feature.leagues.ui.leagues.adapter.LeaguesListAdapter
import com.feature.leagues.ui.leagues.model.LeaguesItemUIModel
import com.feature.leagues.ui.leaguesDetails.LeaguesDetailsActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.core.utils.SpacingItemDecoration

/**
 * A simple [Fragment] subclass.
 */
class LeaguesFragment : BaseFragment<FragmentLeaguesBinding, LeaguesViewModel>() {
    val leaguesViewModel: LeaguesViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isSubFragment = true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLeaguesRecyclerEnvironment()
        leaguesMediatorLiveDataObserver()
        leaguesUIListLiveDataObserver()
        swipeRefreshLayoutListener()
    }

    private fun swipeRefreshLayoutListener() {
        viewDataBinding.swipeRefreshLayout.setOnRefreshListener { leaguesViewModel.getLeaguesList() }
    }

    private fun leaguesUIListLiveDataObserver() {
        viewDataBinding.swipeRefreshLayout.isRefreshing = true
        leaguesViewModel.leaguesUIListLiveData.observe(viewLifecycleOwner, Observer {
            viewDataBinding.adapter?.run {
                leaguesList.clear()
                leaguesList.addAll(it)
                notifyDataSetChanged()
            }
            viewDataBinding.swipeRefreshLayout.isRefreshing = false
        })
    }

    private fun leaguesMediatorLiveDataObserver() {
        leaguesViewModel.leaguesMediatorLiveData.observe(viewLifecycleOwner, Observer {})
    }

    private fun setupLeaguesRecyclerEnvironment() {
        viewDataBinding.run {
            adapter = LeaguesListAdapter(arrayListOf())
            adapter?.leaguesListInterface = object : LeaguesListAdapter.LeaguesInterface {
                override fun onClick(model: LeaguesItemUIModel) {
                    val intent = Intent(context, LeaguesDetailsActivity::class.java)
                    intent.putExtra(LeaguesDetailsActivity.LEAGUEID, model)
                    startActivity(intent)
                }
            }
            leaguesRecycler.addItemDecoration(SpacingItemDecoration(0, 0, 20, 20))
        }
    }

    override fun bindingVariable(): Int = BR.viewModel

    override fun layoutId(): Int = R.layout.fragment_leagues

    override fun getViewModel(): LeaguesViewModel = leaguesViewModel
    override fun handleError() {
        viewDataBinding.swipeRefreshLayout.isRefreshing = false
    }
}
