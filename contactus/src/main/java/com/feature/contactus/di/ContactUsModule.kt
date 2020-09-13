package com.feature.contactus.di

import com.feature.contactus.ui.ContactUsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val contactUsModule = module {
    viewModel { ContactUsViewModel(get()) }
}