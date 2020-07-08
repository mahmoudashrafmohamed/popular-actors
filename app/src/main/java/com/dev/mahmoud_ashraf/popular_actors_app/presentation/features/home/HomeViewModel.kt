package com.dev.mahmoud_ashraf.popular_actors_app.presentation.features.home

import androidx.lifecycle.ViewModel
import com.dev.mahmoud_ashraf.popular_actors_app.domain.repositories.PopularActorsRepository
import io.reactivex.disposables.CompositeDisposable

class HomeViewModel(popularActorsRepository: PopularActorsRepository) : ViewModel() {

    private val compositeDisposable by lazy { CompositeDisposable() }


    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}