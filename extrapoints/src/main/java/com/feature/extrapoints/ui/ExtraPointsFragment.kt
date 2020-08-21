package com.feature.extrapoints.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.core.base.BaseFragment
import com.core.utils.CommonUtils.getRewardedVideoAd
import com.feature.extrapoints.BR
import com.feature.extrapoints.R
import com.feature.extrapoints.databinding.ConfirmAddPointsBinding
import com.feature.extrapoints.databinding.FragmentExtraPointsBinding
import com.google.android.gms.ads.reward.RewardItem
import com.google.android.gms.ads.reward.RewardedVideoAd
import com.google.android.gms.ads.reward.RewardedVideoAdListener
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class ExtraPointsFragment() : BaseFragment<FragmentExtraPointsBinding, ExtraPointsViewModel>() {
    val extraPointsViewModel: ExtraPointsViewModel by viewModel()
    private var mRewardedVideoAd: RewardedVideoAd? = null
    var callBack: CallBack? = null
    var completeWatchingAd = false
    private lateinit var dialogInfo: Dialog
    private lateinit var dialogBinding: ConfirmAddPointsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isSubFragment = true
        setupRewardedVideoAd()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialogInfo = Dialog(requireContext())
        dialogInfo.requestWindowFeature(Window.FEATURE_NO_TITLE)
        extraPointsMediatorLiveDataObserver()
        checkRewardAdAvailabilityLiveDataObserver()
        confirmWatchingRewardAdObserver()
    }

    private fun confirmWatchingRewardAdObserver() {
        extraPointsViewModel.confirmWatchingRewardAd.observe(viewLifecycleOwner, Observer {
            if (it) {
                openConfirmationDialog()
            }
        })
    }

    private fun openConfirmationDialog() {
        val builder = AlertDialog.Builder(requireContext(), R.style.CustomAlertDialog)
        dialogInfo.setCancelable(false)
        dialogBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.confirm_add_points,
                null,
                false
            )
        builder.setView(dialogBinding.root)
        dialogInfo = builder.create()
        dialogBinding.done.setOnClickListener {
            dialogInfo.dismiss()
        }
        dialogInfo.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialogInfo.show()
    }

    private fun checkRewardAdAvailabilityLiveDataObserver() {
        extraPointsViewModel.checkRewardAdAvailabilityLiveData.observe(
            viewLifecycleOwner,
            Observer {
                if (it.data == true) {
                    if (mRewardedVideoAd?.isLoaded!!) {
                        mRewardedVideoAd?.show()
                    } else
                        showMessage(getString(R.string.video_is_not_ready_try_again_later))
                } else {
                    showMessage(getString(R.string.you_can_watch_only_two_videos_per_day_come_back_tomorrow))
                }
            })
    }

    private fun extraPointsMediatorLiveDataObserver() {
        extraPointsViewModel.extraPointsMediatorLiveData.observe(viewLifecycleOwner, Observer {})
    }

    private fun setupRewardedVideoAd() {
        mRewardedVideoAd?.rewardedVideoAdListener = object : RewardedVideoAdListener {
            override fun onRewardedVideoAdClosed() {
                if (completeWatchingAd) {
                    extraPointsViewModel.confirmWatchingAd()
                    completeWatchingAd = false
                }
                callBack?.onVideoAdClosed()
            }

            override fun onRewardedVideoAdLeftApplication() {}

            override fun onRewardedVideoAdLoaded() {}

            override fun onRewardedVideoAdOpened() {
                mRewardedVideoAd = getRewardedVideoAd(requireContext())
                setupRewardedVideoAd()
                callBack?.onVideoAdOpened()
            }

            override fun onRewardedVideoCompleted() {
                completeWatchingAd = true
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
        mRewardedVideoAd?.pause(context)
    }

    override fun onResume() {
        super.onResume()
        mRewardedVideoAd?.resume(context)
    }

    override fun onDestroy() {
        super.onDestroy()
        mRewardedVideoAd?.destroy(context)
    }

    companion object {
        fun newInstance(callBack: CallBack, videoAd: RewardedVideoAd) =
            ExtraPointsFragment(callBack, videoAd)
    }

    constructor(callBack: CallBack, videoAd: RewardedVideoAd) : this() {
        this.callBack = callBack
        mRewardedVideoAd = videoAd
    }

    interface CallBack {
        fun onVideoAdOpened()
        fun onVideoAdClosed()
    }

    override fun handleError() {}
}
