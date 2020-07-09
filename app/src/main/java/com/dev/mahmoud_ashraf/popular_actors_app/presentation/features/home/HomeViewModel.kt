package com.dev.mahmoud_ashraf.popular_actors_app.presentation.features.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dev.mahmoud_ashraf.popular_actors_app.data.entities.Actor
import com.dev.mahmoud_ashraf.popular_actors_app.domain.repositories.PopularActorsRepository
import com.dev.mahmoud_ashraf.popular_actors_app.domain.usecases.fetchPopularActors
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class HomeViewModel(private val popularActorsRepository: PopularActorsRepository) : ViewModel() {

    private val compositeDisposable by lazy { CompositeDisposable() }

    private val _popularActorsLiveData = MutableLiveData<List<Actor>>()
    val popularActorsLiveData : LiveData<List<Actor>>
            get() = _popularActorsLiveData

    private var PAGE_NUM = 1

    init {
        fetchPopularActors()
    }

    fun fetchPopularActors() {
        fetchPopularActors(page = PAGE_NUM, repository = popularActorsRepository)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { actors ->
                    val temp = popularActorsLiveData.value?.toMutableList() ?: mutableListOf()
                    temp.addAll(actors)
                    _popularActorsLiveData.value = temp
                    PAGE_NUM++
                },
                { throwable ->
                    throwable.printStackTrace()
                }

            ).addTo(compositeDisposable)
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

}