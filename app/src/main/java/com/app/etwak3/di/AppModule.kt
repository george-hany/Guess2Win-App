package com.app.etwak3.di

import com.app.etwak3.BuildConfig
import com.core.data.constant.AppConstant.Companion.DATABASE_NAME_KEY
import com.core.data.constant.AppConstant.Companion.PREF_NAME_KEY
import com.app.etwak3.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import com.core.data.constant.AppConstant

val appModule = module {
    single {
        AppConstant(
            BuildConfig.AppID,
            BuildConfig.OrgID
        )
    }

    single(named(PREF_NAME_KEY)) { BuildConfig.PrefName }
    single(named(DATABASE_NAME_KEY)) { BuildConfig.DatabaseName }

    viewModel { MainViewModel(get()) }
}
