package com.core.data.repos

import com.core.data.base.BaseRepo
import com.core.data.constant.SharedPrefKeys
import com.core.data.network.ApiFactory
import com.core.network.NetworkFactoryInterface
import com.core.prefrence.SharedPreference
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainRepo(
    var apiFactory: ApiFactory,
    sharedPreferences: SharedPreference,
    networkFactory: NetworkFactoryInterface
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
}
