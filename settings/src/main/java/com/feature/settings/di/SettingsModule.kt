package com.feature.settings.di

import com.feature.settings.ui.SettingsViewModel
import com.feature.settings.ui.theme.ChangeThemeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val settingsModule = module {
    viewModel { SettingsViewModel(get()) }

    viewModel { ChangeThemeViewModel(get()) }
}