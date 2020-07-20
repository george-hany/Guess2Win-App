package com.core.data.repos

import com.core.data.base.BaseRepo
import com.core.data.constant.SharedPrefKeys
import com.core.data.constant.SharedPrefKeys.Companion.IS_LOGGED_IN
import com.core.data.network.ApiFactory
import com.core.network.NetworkFactoryInterface
import com.core.prefrence.SharedPreference

class BoardRepo(
    var apiFactory: ApiFactory,
    sharedPreferences: SharedPreference,
    networkFactory: NetworkFactoryInterface
) : BaseRepo(sharedPreferences, networkFactory) {
    fun setLoggedInToFalseInSharedPref() {
        sharedPreference.saveBoolean(IS_LOGGED_IN, false)
    }

    fun saveThemeTypeToSharedPref(type: String) {
        sharedPreference.saveString(SharedPrefKeys.THEME, type)
    }
}