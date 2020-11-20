package com.core.utils

import android.app.ProgressDialog
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.annotation.LayoutRes
import com.core.utils.AppConstant.InterstitialId
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.reward.RewardedVideoAd
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.*
import java.util.Locale.*

object CommonUtils {

    fun showLoadingDialog(context: Context, @LayoutRes resId: Int): ProgressDialog {
        val progressDialog = ProgressDialog(context)

        if (progressDialog.window != null) {
            progressDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        progressDialog.apply {
            setContentView(resId)
            isIndeterminate = true
            setCancelable(false)
            setCanceledOnTouchOutside(false)
        }
        progressDialog.show()
        return progressDialog
    }

    fun loadJSONFromAsset(
        context: Context,
        jsonFileName: String
    ): String {
        val manager = context.assets
        val `is`: InputStream = manager.open(jsonFileName)
        val size: Int = `is`.available()
        val buffer = ByteArray(size)
        `is`.read(buffer)
        `is`.close()
        return String(buffer)
    }

    fun updateAppLanguage(langCode: String, res: Resources) {
        val locale = Locale(langCode)
        val config = Configuration(res.configuration)
        setDefault(locale)
        config.setLocale(locale)

        res.updateConfiguration(
            config,
            res.displayMetrics
        )
    }

    fun getCurrentDate(): String {
        val DATE_PICKER_YYYY_MM_DD_FORMAT = SimpleDateFormat("yyyy-MM-dd", getDefault())
        val date = Calendar.getInstance().time
        return DATE_PICKER_YYYY_MM_DD_FORMAT.format(date)
    }

    fun getEnglishDate(date: String): String {
        val DATE_PICKER_YYYY_MM_DD_FORMAT = SimpleDateFormat("yyyy-MM-dd", ENGLISH)
        val myDate = DATE_PICKER_YYYY_MM_DD_FORMAT.parse(date)
        return DATE_PICKER_YYYY_MM_DD_FORMAT.format(myDate)
    }

    fun getDateBeforeOrAfter(date: String, days: Int): String {
        val DATE_PICKER_YYYY_MM_DD_FORMAT = SimpleDateFormat("yyyy-MM-dd", getDefault())
        val myDate = DATE_PICKER_YYYY_MM_DD_FORMAT.parse(date)
        val calendar = Calendar.getInstance()
        calendar.time = myDate!!
        calendar.add(Calendar.DAY_OF_YEAR, days)
        return DATE_PICKER_YYYY_MM_DD_FORMAT.format(calendar.time)
    }

    // 2020-08-08T21:00:00
    fun getTime(date: String): String {
        val DATE_PICKER_YYYY_MM_DD_T_HH_MM_SS_FORMAT =
            SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss", getDefault())
        val myDate = DATE_PICKER_YYYY_MM_DD_T_HH_MM_SS_FORMAT.parse(date)
        val DATE_PICKER_HH_MM_FORMAT = SimpleDateFormat("hh:mm aa", getDefault())
        return DATE_PICKER_HH_MM_FORMAT.format(myDate)
    }

    fun getFacebookPageURL(context: Context): String? {
        val packageManager: PackageManager = context.packageManager
        return try {
            val versionCode: Int =
                packageManager.getPackageInfo("com.facebook.katana", 0).versionCode
            if (versionCode >= 3002850) { // newer versions of fb app
                "fb://facewebmodal/f?href=${AppConstant.FACEBOOK_URL}"
            } else { // older versions of fb app
                "fb://page/${AppConstant.FACEBOOK_PAGE_ID}"
            }
        } catch (e: PackageManager.NameNotFoundException) {
            AppConstant.FACEBOOK_URL // normal web url
        }
    }

    fun getInterstitialAd(context: Context): InterstitialAd {
        val mInterstitialAd = InterstitialAd(context)
        mInterstitialAd.adUnitId = InterstitialId
        mInterstitialAd.loadAd(
            getAdRequest()
        )
        return mInterstitialAd
    }

    fun getAdRequest(): AdRequest =
        AdRequest.Builder().addTestDevice("E30A665A4AA4D5D5C491A7A2F51A0BFE").build()

    fun getRewardedVideoAd(context: Context): RewardedVideoAd {
        val mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(context)
        mRewardedVideoAd.loadVideo()
        return mRewardedVideoAd
    }

    fun RewardedVideoAd.loadVideo() {
        loadAd(
            AppConstant.RewarededVideoAd,
            getAdRequest()
        )
    }
}
