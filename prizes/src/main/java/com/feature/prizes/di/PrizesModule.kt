package com.feature.prizes.di

import com.feature.prizes.ui.PrizesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val prizesModule = module {
    viewModel { PrizesViewModel(get()) }
}