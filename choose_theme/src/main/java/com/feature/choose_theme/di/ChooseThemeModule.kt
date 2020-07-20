package com.feature.choose_theme.di

import com.feature.choose_theme.ui.ChooseThemeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val chooseThemeModule = module {
    viewModel { ChooseThemeViewModel(get()) }
}