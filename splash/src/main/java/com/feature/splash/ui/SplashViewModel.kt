package com.feature.splash.ui

import android.os.Handler
import androidx.lifecycle.MutableLiveData
import com.core.base.BaseViewModel
import com.core.data.repos.SplashRepo

class SplashViewModel(private val splashRepo: SplashRepo) : BaseViewModel<SplashRepo>(splashRepo) {

    val splashTimeOut = MutableLiveData<Boolean>()
    val appLanguage = splashRepo.getAppLanguageFromSharedPref()
    val appTheme = splashRepo.getAppThemeFromSharedPref()
    val isLoggedIn = splashRepo.getIsLoggedInFromSharedPref()

    init {
        startHandler()
    }

    private fun startHandler() {
        Handler().postDelayed({
            splashTimeOut.postValue(true)
        }, 5000)
    }
}
