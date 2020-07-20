package com.core.data.repos

import com.core.data.base.BaseRepo
import com.core.data.constant.SharedPrefKeys.Companion.LANGUAGE
import com.core.network.NetworkFactoryInterface
import com.core.prefrence.SharedPreference

class ChooseLanguageRepo(
    sharedPreferences: SharedPreference,
    networkFactory: NetworkFactoryInterface
) : BaseRepo(sharedPreferences, networkFactory) {
    fun saveLanguageToSharedPref(language: String) {
        sharedPreference.saveString(LANGUAGE, language)
    }
}