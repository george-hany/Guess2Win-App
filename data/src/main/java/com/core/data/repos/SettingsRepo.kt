package com.core.data.repos

import com.core.data.base.BaseRepo
import com.core.data.constant.SharedPrefKeys
import com.core.data.network.ApiFactory
import com.core.network.NetworkFactoryInterface
import com.core.prefrence.SharedPreference
import com.core.utils.FileManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SettingsRepo(
    val apiFactory: ApiFactory,
    sharedPreferences: SharedPreference,
    networkFactory: NetworkFactoryInterface,
    val fileManager: FileManager
) : BaseRepo(sharedPreferences, networkFactory) {
    fun getAppLanguageFromSharedPref(): String? {
        return Gson().fromJson(
            sharedPreference.getString(SharedPrefKeys.LANGUAGE),
            object : TypeToken<String>() {}.type
        )
    }

    fun getAppThemeFromSharedPref(): String? {
        return Gson().fromJson(
            sharedPreference.getString(SharedPrefKeys.THEME),
            object : TypeToken<String>() {}.type
        )
    }

    fun saveLanguageToSharedPref(language: String) {
        sharedPreference.saveString(SharedPrefKeys.LANGUAGE, language)
    }

    fun saveThemeTypeToSharedPref(type: String) {
        sharedPreference.saveString(SharedPrefKeys.THEME, type)
    }

    fun saveNotificationStatusToSharedPref(isDeactivated: Boolean) {
        sharedPreference.saveBoolean(SharedPrefKeys.NOTIFICATION_STATUS, isDeactivated)
    }

    fun getNotificationStatusFromSharedPref(): Boolean =
        sharedPreference.getBoolean(SharedPrefKeys.NOTIFICATION_STATUS)
}