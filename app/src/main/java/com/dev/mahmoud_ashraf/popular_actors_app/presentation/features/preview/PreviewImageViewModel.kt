package com.dev.mahmoud_ashraf.popular_actors_app.presentation.features.preview

import androidx.lifecycle.ViewModel
import com.dev.mahmoud_ashraf.popular_actors_app.domain.repositories.PopularActorsRepository
import com.dev.mahmoud_ashraf.popular_actors_app.domain.usecases.downloadImageUseCase
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

class PreviewImageViewModel(val popularActorsRepository: PopularActorsRepository) : ViewModel() {

    private val compositeDisposable by lazy { CompositeDisposable() }

    init {

    }

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
                Timber.e("downloaded")
            })
            .addTo(compositeDisposable)

    }

    private fun saveImage(body: ResponseBody, externalDirectory: String, fileName: String): Observable<File> {
        return Observable.create {
            var count: Int
            val data = ByteArray(1024 * 4)
            val fileSize = body.contentLength()
            val inputStream = BufferedInputStream(body.byteStream(), 1024 * 8)
            val outputFile = File(externalDirectory, fileName)
            val outputStream = FileOutputStream(outputFile)
            var total: Long = 0

            try {
                count = inputStream.read(data)
                while (count != -1) {
                    total += count.toLong()
                   val  mProgress = ((total * 100).toDouble() / fileSize.toDouble()).toInt()
                    outputStream.write(data, 0, count)

                    count = inputStream.read(data)

                    it.onNext(outputFile)
                }
            } catch (e: OnErrorNotImplementedException) {
                it.onError(e)
            }

            outputStream.flush()
            outputStream.close()
            inputStream.close()

            it.onComplete()
        }
    }



}