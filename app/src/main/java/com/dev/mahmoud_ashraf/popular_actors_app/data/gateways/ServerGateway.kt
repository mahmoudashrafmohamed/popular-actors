package com.dev.mahmoud_ashraf.popular_actors_app.data.gateways

import com.dev.mahmoud_ashraf.popular_actors_app.data.entities.PopularsActorsResponse
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Streaming
import retrofit2.http.Url

interface ServerGateway {

    @GET("person/popular")
    fun getPopularActors(@Query("page") page: Int): Single<PopularsActorsResponse>

    @GET
    @Streaming
    fun downloadImage(@Url url: String): Observable<ResponseBody>

}
