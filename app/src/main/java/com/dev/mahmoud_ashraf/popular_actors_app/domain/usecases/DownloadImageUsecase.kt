package com.dev.mahmoud_ashraf.popular_actors_app.domain.usecases

import com.dev.mahmoud_ashraf.popular_actors_app.domain.repositories.PopularActorsRepository
import io.reactivex.Observable
import io.reactivex.exceptions.OnErrorNotImplementedException
import okhttp3.ResponseBody
import java.io.BufferedInputStream
import java.io.File
import java.io.FileOutputStream

fun downloadImageUseCase(
    url: String?,
    repository: PopularActorsRepository
): Observable<ResponseBody> {
    return repository
        .takeIf { url.isNullOrEmpty().not() }
        ?.downloadImage(url!!)
        ?: Observable.error(Exception())

}

// to save image locally
 fun saveImage(
    body: ResponseBody,
    externalDirectory: String,
    fileName: String
): Observable<File> {
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
                val mProgress = ((total * 100).toDouble() / fileSize.toDouble()).toInt()
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
