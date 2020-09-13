package com.feature.settings.ui.theme

import com.core.base.BaseViewModel
import com.core.data.repos.ChangeThemeRepo

class ChangeThemeViewModel(var changeThemeRepo: ChangeThemeRepo) :
    BaseViewModel<ChangeThemeRepo>(changeThemeRepo) {
    val theme = changeThemeRepo.getAppThemeFromSharedPref()

    fun saveTheme(theme: String) {
        changeThemeRepo.saveThemeTypeToSharedPref(theme)
    }
}