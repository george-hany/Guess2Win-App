package com.feature.choose_language.di

import com.feature.choose_language.ui.ChooseLanguageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val chooseLanguageModule = module {
    viewModel { ChooseLanguageViewModel(get()) }
}