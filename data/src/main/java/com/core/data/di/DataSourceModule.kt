package com.core.data.di

import com.core.data.constant.AppConstant.Companion.PREF_NAME_KEY
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module
import com.core.network.MockedNetwork
import com.core.network.NetworkFactory
import com.core.prefrence.secured_shared_preferences.SecuredSharedPreference
import com.core.prefrence.shared_preferences.AppSharedPreference
import com.core.utils.FileManager

val dataSourceModules = module {
    single { NetworkFactory(androidApplication()) }
    single { MockedNetwork(androidApplication()) }
    single { FileManager(androidApplication()) }
    single {
        AppSharedPreference(
            androidApplication(),
            get(named(PREF_NAME_KEY), parameters = null)
        )
    }
    single {
        SecuredSharedPreference(
            androidApplication(),
            get(named(PREF_NAME_KEY))
        )
    }
}
