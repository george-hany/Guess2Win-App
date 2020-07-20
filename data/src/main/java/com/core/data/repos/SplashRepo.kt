package com.core.data.repos

import com.core.data.base.BaseRepo
import com.core.data.constant.SharedPrefKeys.Companion.IS_LOGGED_IN
import com.core.data.constant.SharedPrefKeys.Companion.LANGUAGE
import com.core.data.constant.SharedPrefKeys.Companion.THEME
import com.core.network.NetworkFactoryInterface
import com.core.prefrence.SharedPreference
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SplashRepo(
    sharedPreferences: SharedPreference,
    networkFactory: NetworkFactoryInterface
) : BaseRepo(sharedPreferences, networkFactory) {
    fun getAppLanguageFromSharedPref(): String? {
        return Gson().fromJson(
            sharedPreference.getString(LANGUAGE),
            object : TypeToken<String>() {}.type
        )
    }

    fun getAppThemeFromSharedPref(): String? {
        return Gson().fromJson(
            sharedPreference.getString(THEME),
            object : TypeToken<String>() {}.type
        )
    }

    fun getIsLoggedInFromSharedPref(): Boolean {
        return sharedPreference.getBoolean(IS_LOGGED_IN)
    }
}
