package com.dev.mahmoud_ashraf.popular_actors_app.domain.usecases

import com.dev.mahmoud_ashraf.popular_actors_app.domain.repositories.PopularActorsRepository
import io.reactivex.Observable
import okhttp3.ResponseBody

fun downloadImageUseCase(
    url: String?,
    repository: PopularActorsRepository
): Observable<ResponseBody> {
    return repository
        .takeIf { url.isNullOrEmpty().not() }
        ?.downloadImage(url!!)
       ?: Observable.error(Exception())

}