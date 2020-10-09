package com.core.utils

import android.Manifest
import android.annotation.TargetApi
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat

object AppConstant {
    const val FACEBOOK_URL = "https://www.facebook.com/etwk3/"
    const val DEVELOPER_URL = "https://www.facebook.com/pateam2020"
    const val FACEBOOK_PAGE_ID = "etwk3"
    const val InterstitialId = "ca-app-pub-3940256099942544/1033173712"
    const val ADMOB_APP_ID = "ca-app-pub-6605239962335205~6494471648"
    const val RewarededVideoAd = "ca-app-pub-3940256099942544/5224354917"
    const val deepLinkArgumentsKey = "android-support-nav:controller:deepLinkIntent"
    const val DAY = "DAY"
    const val NIGHT = "NIGHT"
    const val ARABIC_CODE = "ar"
    const val ENGLISH_CODE = "en"
    const val loginRequest = 2
    const val loginSuccess = 3
    const val NOTIFICATION_KEY = "NOTIFICATION_KEY"
    const val NOTIFICATION_TAG = "NewMessage"
    private val REQUEST_EXTERNAL_STORAGE = 1
    private val PERMISSIONS_STORAGE = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    fun verifyStoragePermissions(activity: Activity): Boolean {
        // Check if we have write permission
        val permissionRead = ActivityCompat.checkSelfPermission(
            activity,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
        val permissionWrite =
            ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        return if (permissionRead != PackageManager.PERMISSION_GRANTED ||
            permissionWrite != PackageManager.PERMISSION_GRANTED
        ) { // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                activity,
                PERMISSIONS_STORAGE,
                REQUEST_EXTERNAL_STORAGE
            )
            false
        } else {
            true
        }
    }
}
