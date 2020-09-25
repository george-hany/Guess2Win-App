package com.core.utils

import android.app.Activity
import android.os.Handler
import com.core.utils.CommonUtils.getInterstitialAd
import com.mopub.mobileads.MoPubErrorCode
import com.mopub.mobileads.MoPubInterstitial

class InterstitialAdManager(var activity: Activity) {
    lateinit var interstitialAd: MoPubInterstitial
    var isStopped = false

    init {
        setupInterstitialAd()
//        interstitialAdListener()
    }

    private fun setupInterstitialAd() {
        interstitialAd = getInterstitialAd(activity)
        interstitialAd.interstitialAdListener = object : MoPubInterstitial.InterstitialAdListener {
            override fun onInterstitialLoaded(interstitial: MoPubInterstitial?) {}

            override fun onInterstitialFailed(
                interstitial: MoPubInterstitial?,
                errorCode: MoPubErrorCode?
            ) {
            }

            override fun onInterstitialShown(interstitial: MoPubInterstitial?) {}

            override fun onInterstitialClicked(interstitial: MoPubInterstitial?) {}

            override fun onInterstitialDismissed(interstitial: MoPubInterstitial?) {
                setupInterstitialAd()
                startInterstitialAd()
            }
        }
        interstitialAd.load()
    }

//    fun loadInterstitialAd() {
//        interstitialAd.loadAd(
//            AdRequest.Builder().addTestDevice("E30A665A4AA4D5D5C491A7A2F51A0BFE").build()
//        )
//    }

//    fun interstitialAdListener() {
//        loadInterstitialAd()
//        interstitialAd.adListener = object : AdListener() {
//            override fun onAdClosed() {
//                super.onAdClosed()
//                loadInterstitialAd()
//                startInterstitialAd()
//            }
//        }
//    }

    fun startInterstitialAd() {
        isStopped = false
        Handler().postDelayed({ showInterstitialAd() }, 60000)
    }

    private fun showInterstitialAd() {
        interstitialAd.let {
            if (!isStopped) {
                if (it.isReady) {
                    it.show()
                } else {
                    setupInterstitialAd()
                }
            }
        }
    }

    fun stopInterstitialAd() {
        isStopped = true
    }

    fun destroyAd() {
        interstitialAd.destroy()
    }
}