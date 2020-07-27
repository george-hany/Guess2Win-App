package com.feature.board.di

import com.feature.board.ui.BoardViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val boardModule = module {
    viewModel { BoardViewModel(get()) }
}