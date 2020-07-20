package com.feature.extrapoints.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.core.base.BaseFragment
import com.core.utils.AppConstant.RewarededVideoAd
import com.feature.extrapoints.BR
import com.feature.extrapoints.R
import com.feature.extrapoints.databinding.FragmentExtraPointsBinding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.reward.RewardItem
import com.google.android.gms.ads.reward.RewardedVideoAd
import com.google.android.gms.ads.reward.RewardedVideoAdListener
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class ExtraPointsFragment : BaseFragment<FragmentExtraPointsBinding, ExtraPointsViewModel>() {
    val extraPointsViewModel: ExtraPointsViewModel by viewModel()
    private lateinit var mRewardedVideoAd: RewardedVideoAd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isSubFragment = true
        setupRewardedVideoAd()
        loadRewardedVideoAd()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        watchButtonListener()
    }

    private fun watchButtonListener() {
        viewDataBinding.watch.setOnClickListener {
            if (mRewardedVideoAd.isLoaded) {
                mRewardedVideoAd.show()
            } else
                activity?.toast("video is not ready try again later")
        }
    }

    private fun loadRewardedVideoAd() {
        mRewardedVideoAd.loadAd(
            RewarededVideoAd,
            AdRequest.Builder().build()
        )
    }

    private fun setupRewardedVideoAd() {
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(context)
        mRewardedVideoAd.rewardedVideoAdListener = object : RewardedVideoAdListener {
            override fun onRewardedVideoAdClosed() {
                loadRewardedVideoAd()
            }

            override fun onRewardedVideoAdLeftApplication() {}

            override fun onRewardedVideoAdLoaded() {}

            override fun onRewardedVideoAdOpened() {}

            override fun onRewardedVideoCompleted() {
                activity?.toast("rewarded successfully")
                loadRewardedVideoAd()
            }

            override fun onRewarded(p0: RewardItem?) {}

            override fun onRewardedVideoStarted() {}

            override fun onRewardedVideoAdFailedToLoad(p0: Int) {}
        }
    }

    override fun bindingVariable(): Int = BR.viewModel

    override fun layoutId(): Int = R.layout.fragment_extra_points

    override fun getViewModel(): ExtraPointsViewModel = extraPointsViewModel

    override fun onPause() {
        super.onPause()
        mRewardedVideoAd.pause(context)
    }

    override fun onResume() {
        super.onResume()
        mRewardedVideoAd.resume(context)
    }

    override fun onDestroy() {
        super.onDestroy()
        mRewardedVideoAd.destroy(context)
    }
}
