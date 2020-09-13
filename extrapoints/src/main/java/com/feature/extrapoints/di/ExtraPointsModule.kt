package com.feature.extrapoints.di

import com.feature.extrapoints.ui.ExtraPointsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val extraPointsModule = module {
    viewModel { ExtraPointsViewModel(get()) }
}