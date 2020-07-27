package com.feature.matches.di

import com.feature.matches.ui.matchDetails.MatchDetailsViewModel
import com.feature.matches.ui.matches.MatchesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val matchesModule = module {
    viewModel { MatchesViewModel(get()) }

    viewModel { MatchDetailsViewModel(get()) }
}