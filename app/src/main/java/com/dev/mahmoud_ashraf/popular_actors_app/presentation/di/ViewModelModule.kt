package com.dev.mahmoud_ashraf.popular_actors_app.presentation.di

import com.dev.mahmoud_ashraf.popular_actors_app.presentation.features.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel {
        HomeViewModel(
            get()
        )
    }
}