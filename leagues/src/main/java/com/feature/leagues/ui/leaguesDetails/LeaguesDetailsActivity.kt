package com.feature.leagues.ui.leaguesDetails

import android.os.Bundle
import com.core.base.BaseActivity
import com.feature.leagues.BR
import com.feature.leagues.R
import com.feature.leagues.databinding.ActivityLeaguesDetailsBinding
import com.feature.leagues.ui.leagues.model.LeaguesItemUIModel
import com.feature.leagues.ui.leaguesDetails.adapter.SectionsPagerAdapter
import com.google.android.gms.ads.AdRequest
import org.koin.androidx.viewmodel.ext.android.viewModel

class LeaguesDetailsActivity :
    BaseActivity<ActivityLeaguesDetailsBinding, LeaguesDetailsViewModel>() {
    var leagueItemUIModel: LeaguesItemUIModel? = null
    val leaguesDetailsViewModel: LeaguesDetailsViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        leagueItemUIModel = intent.getParcelableExtra(LEAGUEID)
        leaguesDetailsViewModel.leagueItemUIModel.value = leagueItemUIModel
        setupViewPager()
        backArrowListener()
        setupAdView()
    }
    private fun setupAdView() {
        val adRequest =
            AdRequest.Builder().addTestDevice("E30A665A4AA4D5D5C491A7A2F51A0BFE").build()
        viewDataBinding.adView.run {
            loadAd(adRequest)
        }
    }
    private fun setupViewPager() {
        val sectionsPagerAdapter =
            SectionsPagerAdapter(this, supportFragmentManager, leagueItemUIModel?.id ?: "")
        viewDataBinding.run {
            viewPager.adapter = sectionsPagerAdapter
            viewPager.offscreenPageLimit = 1
            tabs.setupWithViewPager(viewPager)
        }
    }

    private fun backArrowListener() {
        viewDataBinding.backArrow.setOnClickListener { onBackPressed() }
    }

    override fun bindingVariable(): Int = BR.viewModel

    override fun layoutId(): Int = R.layout.activity_leagues_details

    override fun controllerId(): Int = 0

    override fun getViewModel(): LeaguesDetailsViewModel = leaguesDetailsViewModel

    companion object {
        const val LEAGUEID = "LEAGUEID"
    }
}
