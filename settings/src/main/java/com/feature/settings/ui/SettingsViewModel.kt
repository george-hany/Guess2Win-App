package com.feature.settings.ui

import com.core.base.BaseViewModel
import com.core.data.repos.SettingsRepo

class SettingsViewModel(var settingsRepo: SettingsRepo) :
    BaseViewModel<SettingsRepo>(settingsRepo) {

    val notificationStatus = settingsRepo.getNotificationStatusFromSharedPref()
    val themeStatus = settingsRepo.getAppThemeFromSharedPref()
    val language = settingsRepo.getAppLanguageFromSharedPref()

    fun saveLanguage(language: String) {
        settingsRepo.saveLanguageToSharedPref(language)
    }

    fun saveTheme(theme: String) {
        settingsRepo.saveThemeTypeToSharedPref(theme)
    }

    fun saveNotificationStatus(isDeactivated: Boolean) {
        settingsRepo.saveNotificationStatusToSharedPref(isDeactivated)
    }
}