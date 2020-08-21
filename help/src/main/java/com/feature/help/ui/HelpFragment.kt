package com.feature.help.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.core.base.BaseFragment
import com.core.utils.SpacingItemDecoration
import com.feature.help.BR
import com.feature.help.R
import com.feature.help.databinding.FragmentHelpBinding
import com.feature.help.ui.adapter.HelpListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class HelpFragment : BaseFragment<FragmentHelpBinding, HelpViewModel>() {
    val helpViewModel: HelpViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isSubFragment = true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupPrizesRecycler()
        prizesMediatorLiveDataObserver()
        prizesUIListLiveDataObserver()
        swipeRefreshLayoutListener()
    }

    private fun swipeRefreshLayoutListener() {
        viewDataBinding.swipeRefreshLayout.setOnRefreshListener { helpViewModel.getHelpsList() }
    }

    private fun prizesUIListLiveDataObserver() {
        viewDataBinding.swipeRefreshLayout.isRefreshing = true
        helpViewModel.helpsItemUIListLiveData.observe(viewLifecycleOwner, Observer {
            viewDataBinding.adapter?.run {
                helpItemsList.clear()
                helpItemsList.addAll(it)
                notifyDataSetChanged()
            }
            viewDataBinding.swipeRefreshLayout.isRefreshing = false
        })
    }

    private fun prizesMediatorLiveDataObserver() {
        helpViewModel.helpsMediatorLiveData.observe(viewLifecycleOwner, Observer {})
    }

    private fun setupPrizesRecycler() {
        viewDataBinding.run {
            adapter = HelpListAdapter(arrayListOf())
            prizesRecycler.addItemDecoration(SpacingItemDecoration(0, 0, 20, 20))
        }
    }

    override fun bindingVariable(): Int = BR.viewModel

    override fun layoutId(): Int = R.layout.fragment_help

    override fun getViewModel(): HelpViewModel = helpViewModel

    override fun handleError() {}
}
