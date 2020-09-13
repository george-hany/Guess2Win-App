package com.core.utils

import android.content.Context
import android.os.Handler
import com.core.utils.CommonUtils.getInterstitialAd
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.InterstitialAd

class InterstitialAdManager(var context: Context) {
    lateinit var interstitialAd: InterstitialAd
    var isStopped = false

    init {
        setupInterstitialAd()
//        interstitialAdListener()
    }

    private fun setupInterstitialAd() {
        interstitialAd = getInterstitialAd(context)
        interstitialAd.adListener = object : AdListener() {
            override fun onAdClosed() {
                super.onAdClosed()
                setupInterstitialAd()
                startInterstitialAd()
            }
        }
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
                if (it.isLoaded) {
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
}