package com.core.utils

import android.content.Context
import android.os.Handler
import android.os.Looper
import com.core.utils.AppConstant.InterstitialId
import com.google.android.gms.ads.*
import com.google.android.gms.ads.reward.RewardItem
import com.google.android.gms.ads.reward.RewardedVideoAd
import com.google.android.gms.ads.reward.RewardedVideoAdListener

class AdManage {
    constructor(adView: AdView?, context: Context?) {
        Companion.adView = adView
        Companion.context = context
        rewardedVideoAd = MobileAds.getRewardedVideoAdInstance(context)
        interstitialAd = InterstitialAd(context)
        rewardedVideoAd.rewardedVideoAdListener = object :
            RewardedVideoAdListener {
            override fun onRewardedVideoAdLoaded() {
                showInterstitialAd()
            }

            override fun onRewardedVideoAdOpened() {}
            override fun onRewardedVideoStarted() {}
            override fun onRewardedVideoAdClosed() {}
            override fun onRewarded(rewardItem: RewardItem) {}
            override fun onRewardedVideoAdLeftApplication() {}
            override fun onRewardedVideoAdFailedToLoad(i: Int) {
                // Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
            }

            override fun onRewardedVideoCompleted() {}
        }
        interstitialAd.adListener = object : AdListener() {
            override fun onAdLoaded() {
                showInterstitialAd()
            }

            override fun onAdFailedToLoad(errorCode: Int) {
                // Code to be executed when an ad request fails.
            }

            override fun onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            override fun onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            override fun onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            override fun onAdClosed() {
                // Code to be executed when the interstitial ad is closed.
            }
        }
    }

    constructor(context: Context?) {
        Companion.context = context
        rewardedVideoAd = MobileAds.getRewardedVideoAdInstance(context)
        setupInterstitialAd(context)
        loadInterstitialAd()
    }

    private fun setupInterstitialAd(context: Context?) {
        interstitialAd = InterstitialAd(context)
        interstitialAd.adUnitId = InterstitialId
    }

    fun reStartAdView() {
        MobileAds.initialize(context) { }
        adRequest = AdRequest.Builder().build()
        adView!!.loadAd(adRequest)
    }

    fun startRewardedVideoAd() {
        MobileAds.initialize(context) { }
        rewardedVideoAd.loadAd(
            rewardedVideoAdID,
            AdRequest.Builder().build()
        )
    }

    private fun startRewardedVideoAd2() {
        if (rewardedVideoAd.isLoaded) {
            rewardedVideoAd.show()
        } else {
            // Toast.makeText(context, "filed 1", Toast.LENGTH_SHORT).show();
            if (bRewardedVideoAd) {
                // startRewardedVideoAd2();
            }
        }
    }

    fun loadInterstitialAd() {
        interstitialAd.loadAd(
            AdRequest.Builder().addTestDevice("E30A665A4AA4D5D5C491A7A2F51A0BFE").build()
        )
        interstitialAd.adListener = object : AdListener() {
            override fun onAdClosed() {
                super.onAdClosed()
                interstitialAd.loadAd(
                    AdRequest.Builder().addTestDevice("E30A665A4AA4D5D5C491A7A2F51A0BFE").build()
                )
            }
        }
    }

    private fun showInterstitialAd() {
        if (interstitialAd.isLoaded) {
            interstitialAd.show()
            loadInterstitialAd()
        } else {
            // Toast.makeText(context, "filed 2", Toast.LENGTH_SHORT).show();
            if (bInterstitialAd) {
                showInterstitialAd()
            }
        }
    }

    fun startOne() {
        reStartAdView()
        val runAdView = RunAdView()
        Thread(runAdView).start()
    }

    fun startInterstitialAd() {
        bInterstitialAd = false
        showInterstitialAd()
        val runAdView = RunInterstitialAd()
        Thread(runAdView).start()
    }

    fun startThree() {
        // startRewardedVideoAd();
        val runAdView = RunAdVideo()
        Thread(runAdView).start()
    }

    fun startAll() {
        startOne()
        startInterstitialAd()
        startThree()
    }

    fun stopInterstitialAd() {
        bInterstitialAd = false
    }

    fun stopAll(b: Boolean) {
        bAdView = b
        bInterstitialAd = b
        bRewardedVideoAd = b
    }

    fun getbAdView(): Boolean {
        return bAdView
    }

    fun setbAdView(bAdView: Boolean) {
        Companion.bAdView = bAdView
    }

    fun getbInterstitialAd(): Boolean {
        return bInterstitialAd
    }

    fun setbInterstitialAd(bInterstitialAd: Boolean) {
        Companion.bInterstitialAd = bInterstitialAd
    }

    fun getbRewardedVideoAd(): Boolean {
        return bRewardedVideoAd
    }

    fun setbRewardedVideoAd(bRewardedVideoAd: Boolean) {
        Companion.bRewardedVideoAd = bRewardedVideoAd
    }

    private inner class RunAdView : Runnable {
        override fun run() {
            while (true) {
                if (bAdView) {
                    return
                } else {
                    try {
                        // runOnUiThread
                        val handler = Handler(Looper.getMainLooper())
                        handler.post { reStartAdView() }
                        Thread.sleep(60000)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }

    private inner class RunAdVideo : Runnable {
        override fun run() {
            while (true) {
                if (bRewardedVideoAd == true) {
                    return
                } else {
                    try {
                        Thread.sleep(29999)
                        val handler = Handler(Looper.getMainLooper())
                        handler.post { startRewardedVideoAd() }
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }

    private inner class RunInterstitialAd : Runnable {
        override fun run() {
            try {
                Thread.sleep(3000)
                val handler = Handler(Looper.getMainLooper())
                handler.post { showInterstitialAd() }
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }

    companion object {
        lateinit var interstitialAd: InterstitialAd
        private var adView: AdView? = null
        private var adRequest: AdRequest? = null
        lateinit var rewardedVideoAd: RewardedVideoAd
        private var context: Context? = null
        private const val adViewID = "ca-app-pub-4103869349675854/8531366956"
        private const val rewardedVideoAdID = "ca-app-pub-4103869349675854/2895896890"

        /**
         * private static String  adViewID="ca-app-pub-4103869349675854/1531045654",
         * interstitialAdID="ca-app-pub-4103869349675854/1339473960",
         * rewardedVideoAdID="ca-app-pub-4103869349675854/6017085577";
         *
         * private static String  adViewID="ca-app-pub-3940256099942544/6300978111",
         * interstitialAdID="ca-app-pub-3940256099942544/1033173712",
         * rewardedVideoAdID="ca-app-pub-3940256099942544/5224354917";
         */
        @Volatile
        private var bAdView = false

        @Volatile
        private var bInterstitialAd = false

        @Volatile
        private var bRewardedVideoAd = false
    }
}