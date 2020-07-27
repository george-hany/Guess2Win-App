package com.feature.prizes.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import com.core.base.BaseFragment
import com.feature.prizes.BR
import com.feature.prizes.R
import com.feature.prizes.databinding.FragmentPrizesBinding
import com.feature.prizes.ui.adapter.PrizesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.core.utils.SpacingItemDecoration

/**
 * A simple [Fragment] subclass.
 */
class PrizesFragment : BaseFragment<FragmentPrizesBinding, PrizesViewModel>() {
    val prizesViewModel: PrizesViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isSubFragment = true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupPrizesRecycler()
        prizesMediatorLiveDataObserver()
        prizesUIListLiveDataObserver()
    }

    private fun prizesUIListLiveDataObserver() {
        prizesViewModel.prizesUIListLiveData.observe(viewLifecycleOwner, Observer {
            viewDataBinding.adapter?.run {
                prizesList.clear()
                prizesList.addAll(it)
                notifyDataSetChanged()
            }
        })
    }

    private fun prizesMediatorLiveDataObserver() {
        prizesViewModel.prizesMediatorLiveData.observe(viewLifecycleOwner, Observer {})
    }

    private fun setupPrizesRecycler() {
        viewDataBinding.run {
            adapter = PrizesAdapter(arrayListOf())
            prizesRecycler.addItemDecoration(SpacingItemDecoration(0, 0, 20, 20))
        }
    }

    override fun bindingVariable(): Int = BR.viewModel

    override fun layoutId(): Int = R.layout.fragment_prizes

    override fun getViewModel(): PrizesViewModel = prizesViewModel
}
