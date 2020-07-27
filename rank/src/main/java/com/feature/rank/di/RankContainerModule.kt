package com.feature.rank.di

import com.feature.rank.ui.rank.RankViewModel
import com.feature.rank.ui.rankContainer.RankContainerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val rankContainerModule = module {
    viewModel { RankContainerViewModel(get()) }
    viewModel { RankViewModel(get()) }
}