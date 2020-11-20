package com.feature.matches.ui.matchDetails

import android.os.Bundle
import androidx.lifecycle.Observer
import com.core.base.BaseActivity
import com.core.utils.CommonUtils.getAdRequest
import com.feature.matches.BR
import com.feature.matches.R
import com.feature.matches.databinding.ActivityMatchDetailsBinding
import com.feature.matches.ui.matches.model.MatchItemUIModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MatchDetailsActivity : BaseActivity<ActivityMatchDetailsBinding, MatchDetailsViewModel>() {
    val matchDetailsViewModel: MatchDetailsViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val match = intent.getParcelableExtra<MatchItemUIModel>(MATCH_ID)
        matchDetailsViewModel.getMatchDetails(match?.matchId ?: "")
        matchDetailsViewModel.matchExpectationRequest.matchId = match?.matchId?.toInt()
        matchDetailsMediatorLiveDataObserver()
        matchDetailsUIModelObserver()
        setUpFirstTeamNP()
        setUpSecondTeamNP()
        matchExpectationResponseObserver()
        backArrowListener()
        setupAdView()
    }

    private fun setupAdView() {
        viewDataBinding.adView.run {
            loadAd(getAdRequest())
        }
    }

    private fun backArrowListener() {
        viewDataBinding.backArrow.setOnClickListener { onBackPressed() }
    }

    private fun matchExpectationResponseObserver() {
        matchDetailsViewModel.matchExpectationResponse.observe(this, Observer {
            showMessage(R.string.expectation_added_successfully)
        })
    }

    private fun setUpFirstTeamNP() {
        viewDataBinding.firstTeamNP.run {
            maxValue = 100
            minValue = 0
            setOnValueChangedListener { picker, oldVal, newVal ->
                matchDetailsViewModel.matchExpectationRequest.firstTeamScore = newVal
            }
        }
    }

    private fun setUpSecondTeamNP() {
        viewDataBinding.secondTeamNP.run {
            maxValue = 100
            minValue = 0
            setOnValueChangedListener { picker, oldVal, newVal ->
                matchDetailsViewModel.matchExpectationRequest.secondTeamScore = newVal
            }
        }
    }

    private fun matchDetailsUIModelObserver() {
        matchDetailsViewModel.matchItemUIModel.observe(this, Observer {
            viewDataBinding.firstTeamNP.value =
                it?.pridictionNumberGoalsOfTeam1?.toInt() ?: 0
            viewDataBinding.secondTeamNP.value =
                it?.pridictionNumberGoalsOfTeam2?.toInt() ?: 0
            matchDetailsViewModel.matchExpectationRequest.run {
                firstTeamScore = it.pridictionNumberGoalsOfTeam1?.toInt() ?: 0
                secondTeamScore = it.pridictionNumberGoalsOfTeam2?.toInt() ?: 0
            }
        })
    }

    private fun matchDetailsMediatorLiveDataObserver() {
        matchDetailsViewModel.matchDetailsMediatorLiveData.observe(this, Observer {})
    }

    override fun bindingVariable(): Int = BR.viewModel

    override fun layoutId(): Int = R.layout.activity_match_details

    override fun controllerId(): Int = 0

    override fun getViewModel(): MatchDetailsViewModel = matchDetailsViewModel

    companion object {
        const val MATCH_ID = "MATCH_ID"
    }
}
