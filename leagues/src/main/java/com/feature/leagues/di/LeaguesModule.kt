package com.feature.leagues.di

import com.feature.leagues.ui.leagues.LeaguesViewModel
import com.feature.leagues.ui.leaguesDetails.LeaguesDetailsViewModel
import com.feature.leagues.ui.leaguesRank.LeaguesRankViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val leaguesModule = module {
    viewModel { LeaguesViewModel(get()) }
    viewModel { LeaguesDetailsViewModel(get()) }
    viewModel { LeaguesRankViewModel(get()) }
}