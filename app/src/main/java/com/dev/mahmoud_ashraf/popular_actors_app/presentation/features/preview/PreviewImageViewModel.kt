package com.dev.mahmoud_ashraf.popular_actors_app.presentation.features.preview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dev.mahmoud_ashraf.popular_actors_app.data.entities.Actor
import com.dev.mahmoud_ashraf.popular_actors_app.domain.repositories.PopularActorsRepository
import com.dev.mahmoud_ashraf.popular_actors_app.domain.usecases.downloadImageUseCase
import com.dev.mahmoud_ashraf.popular_actors_app.domain.usecases.saveImage
import com.dev.mahmoud_ashraf.popular_actors_app.presentation.core.SingleLiveEvent
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.exceptions.OnErrorNotImplementedException
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import timber.log.Timber
import java.io.BufferedInputStream
import java.io.File
import java.io.FileOutputStream

class PreviewImageViewModel(private val popularActorsRepository: PopularActorsRepository) : ViewModel() {

    private val compositeDisposable by lazy { CompositeDisposable() }

    // to trigger event only once
    val downloadStatusLiveData = SingleLiveEvent<Boolean>()

    fun download(url : String,fileName: String, externalDirectory: String) {

        downloadImageUseCase(url, popularActorsRepository)
            .flatMap {
                saveImage(it, externalDirectory, fileName)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onError = {
                it.printStackTrace()
            }, onNext = {
                Timber.e(""+it)
            }, onComplete = {
               downloadStatusLiveData.value = true
            })
            .addTo(compositeDisposable)

    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

}