package com.core.utils

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import androidx.core.content.ContextCompat
import com.core.utils.AppConstant.InterstitialId
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd

class InterstitialAdService : Service() {
    override fun onBind(intent: Intent?): IBinder? = null

    companion object {
        var interstitialAd: InterstitialAd? = null
        lateinit var con: Context
        fun startService(context: Context) {
            val startIntent = Intent(context, InterstitialAdService::class.java)
            ContextCompat.startForegroundService(context, startIntent)
            setupInterstitialAd(context)
            con = context
        }

        fun stopService(context: Context) {
            val stopIntent = Intent(context, InterstitialAdService::class.java)
            context.stopService(stopIntent)
        }

        fun setupInterstitialAd(context: Context?) {
            interstitialAd = InterstitialAd(context)
            interstitialAd?.adUnitId = InterstitialId
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        interstitialAdListener()
        startInterstitialAd()
        return START_NOT_STICKY
    }

    fun interstitialAdListener() {
        loadInterstitialAd()
        interstitialAd?.adListener = object : AdListener() {
            override fun onAdClosed() {
                super.onAdClosed()
                loadInterstitialAd()
                startInterstitialAd()
            }
        }
    }

    fun startInterstitialAd() {
//        showInterstitialAd()
        Handler().postDelayed({ showInterstitialAd() }, 5000)
    }

    private fun showInterstitialAd() {
        interstitialAd?.let {
            if (it.isLoaded) {
                it.show()
            } else {
                loadInterstitialAd()
                startInterstitialAd()
            }
        }
    }

    fun loadInterstitialAd() {
        interstitialAd?.loadAd(
            AdRequest.Builder().addTestDevice("E30A665A4AA4D5D5C491A7A2F51A0BFE").build()
        )
    }
//
//    private inner class RunInterstitialAd : Runnable {
//        override fun run() {
//            try {
//
//            } catch (e: InterruptedException) {
//                e.printStackTrace()
//            }
//        }
//    }

    override fun onDestroy() {
        super.onDestroy()
        interstitialAd = null
    }
}