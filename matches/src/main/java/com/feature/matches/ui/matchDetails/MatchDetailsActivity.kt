package com.feature.matches.ui.matchDetails

import android.os.Bundle
import androidx.lifecycle.Observer
import com.core.base.BaseActivity
import com.core.utils.CommonUtils
import com.feature.matches.BR
import com.feature.matches.R
import com.feature.matches.databinding.ActivityMatchDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MatchDetailsActivity : BaseActivity<ActivityMatchDetailsBinding, MatchDetailsViewModel>() {
    val matchDetailsViewModel: MatchDetailsViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val matchId = intent.getStringExtra(MATCH_ID)
        matchDetailsViewModel.getMatchDetails(matchId ?: "")
        matchDetailsViewModel.matchExpectationRequest.matchId = matchId
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
            loadAd(CommonUtils.getAdRequest())
        }
    }
    private fun backArrowListener() {
        viewDataBinding.backArrow.setOnClickListener { onBackPressed() }
    }

    private fun matchExpectationResponseObserver() {
        matchDetailsViewModel.matchExpectationResponse.observe(this, Observer {
            toast(it.message)
        })
    }

    private fun setUpFirstTeamNP() {
        viewDataBinding.firstTeamNP.run {
            maxValue = 100
            minValue = 0
            setOnValueChangedListener { picker, oldVal, newVal ->
                matchDetailsViewModel.matchExpectationRequest.firstTeamScore = newVal.toString()
            }
        }
    }

    private fun setUpSecondTeamNP() {
        viewDataBinding.secondTeamNP.run {
            maxValue = 100
            minValue = 0
            setOnValueChangedListener { picker, oldVal, newVal ->
                matchDetailsViewModel.matchExpectationRequest.secondTeamScore = newVal.toString()
            }
        }
    }

    private fun matchDetailsUIModelObserver() {
        matchDetailsViewModel.matchDetailsUIModel.observe(this, Observer {
            if (!it.userExpectation.isNullOrEmpty()) {
                viewDataBinding.firstTeamNP.value =
                    it?.userExpectation?.split(':')?.get(0)?.toInt() ?: 0
                viewDataBinding.secondTeamNP.value =
                    it?.userExpectation?.split(':')?.get(1)?.toInt() ?: 0
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
