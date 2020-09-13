package com.feature.help.di

import com.feature.help.ui.HelpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val helpModule = module {
    viewModel { HelpViewModel(get()) }
}