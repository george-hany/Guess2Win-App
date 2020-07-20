package com.app.etwak3.ui

import com.core.base.BaseViewModel
import com.core.data.repos.MainRepo

class MainViewModel(val mainRepo: MainRepo) :
    BaseViewModel<MainRepo>(mainRepo) {
    val appLanguage = mainRepo.getAppLanguageFromSharedPref()
    val appTheme = mainRepo.getAppThemeFromSharedPref()

    fun getTheme() = mainRepo.getAppThemeFromSharedPref()
}
