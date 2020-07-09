package com.dev.mahmoud_ashraf.popular_actors_app.presentation.features.home

import androidx.lifecycle.ViewModel
import com.dev.mahmoud_ashraf.popular_actors_app.domain.repositories.PopularActorsRepository
import com.dev.mahmoud_ashraf.popular_actors_app.domain.usecases.fetchPopularActors
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class HomeViewModel(private val popularActorsRepository: PopularActorsRepository) : ViewModel() {

    private val compositeDisposable by lazy { CompositeDisposable() }

    fun load(){}

    init {
        fetchPopularActors(page = 1, repository = popularActorsRepository)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { data ->
                  //  _venuesListLiveData.value = data
                    Timber.e(data.toString())
                },
                { throwable ->
                    throwable.printStackTrace()
                    Timber.e("error happened")
                }
            ).addTo(compositeDisposable)
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}