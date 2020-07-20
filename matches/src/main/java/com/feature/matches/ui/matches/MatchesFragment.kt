package com.feature.matches.ui.matches

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.core.base.BaseFragment
import com.feature.matches.BR
import com.feature.matches.R
import com.feature.matches.databinding.FragmentMatchesBinding
import com.feature.matches.ui.matchDetails.MatchDetailsActivity
import com.feature.matches.ui.matches.adapter.MatchesListAdapter
import com.feature.matches.ui.matches.model.MatchesFragmentModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.core.utils.SpacingItemDecoration

/**
 * A simple [Fragment] subclass.
 */
class MatchesFragment : BaseFragment<FragmentMatchesBinding, MatchesViewModel>() {
    val matchesViewModel: MatchesViewModel by viewModel()
    var matchesFragmentModel: MatchesFragmentModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isSubFragment = true
        matchesFragmentModel = arguments?.getParcelable(MODEL)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpMatchesRecyclerEnvironment()
        selectedDateObserver()
        matchesMediatorLiveDataObserver()
        matchesListLiveDataObserver()
    }

    private fun setUpMatchesRecyclerEnvironment() {
        viewDataBinding.run {
            adapter = MatchesListAdapter(arrayListOf())
            matchesRecycler.addItemDecoration(SpacingItemDecoration(0, 0, 20, 20))
            adapter?.matchesListInterface = object : MatchesListAdapter.MatchesListInterface {
                override fun onClick(matchId: String) {
                    val intent = Intent(context, MatchDetailsActivity::class.java)
                    intent.putExtra(MatchDetailsActivity.MATCH_ID, matchId)
                    startActivity(intent)
                }
            }
        }
    }

    private fun matchesListLiveDataObserver() {
        matchesViewModel.matchesListLiveData.observe(viewLifecycleOwner, Observer {
            viewDataBinding.adapter?.run {
                matchesList.clear()
                matchesList.addAll(it)
                notifyDataSetChanged()
            }
        })
    }

    private fun matchesMediatorLiveDataObserver() {
        matchesViewModel.matchesMediatorLiveData.observe(viewLifecycleOwner, Observer {})
    }

    private fun selectedDateObserver() {
        matchesViewModel.selectedDate.observe(viewLifecycleOwner, Observer {
            matchesFragmentModel?.run {
                matchesViewModel.getLeaguesMatchesByDate(it, leagueId ?: "")
            } ?: kotlin.run {
                matchesViewModel.getMatchesByDate(it)
            }
        })
    }

    override fun bindingVariable(): Int = BR.viewModel

    override fun layoutId(): Int = R.layout.fragment_matches

    override fun getViewModel(): MatchesViewModel = matchesViewModel

    companion object {
        fun newInstance(model: MatchesFragmentModel): MatchesFragment = MatchesFragment().apply {
            arguments = Bundle().apply {
                putParcelable(MODEL, model)
            }
        }

        val MODEL = "MODEL"
    }
}
