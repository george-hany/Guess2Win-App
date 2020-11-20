package com.feature.leagues.ui.leaguesDetails

import android.os.Bundle
import android.util.LayoutDirection
import android.view.View
import com.core.base.BaseActivity
import com.core.utils.CommonUtils
import com.core.utils.CommonUtils.getAdRequest
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
        viewDataBinding.adView.run {
            loadAd(getAdRequest())
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
