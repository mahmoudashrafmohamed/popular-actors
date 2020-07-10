package com.dev.mahmoud_ashraf.popular_actors_app.domain.repositories

import com.dev.mahmoud_ashraf.popular_actors_app.data.entities.PopularsActorsResponse
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.ResponseBody

interface PopularActorsRepository {

    fun requestPopularActors(page: Int): Single<PopularsActorsResponse>

    fun downloadImage(url: String): Observable<ResponseBody>
}