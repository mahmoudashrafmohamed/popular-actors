package com.dev.mahmoud_ashraf.popular_actors_app.data.gateways

import com.dev.mahmoud_ashraf.popular_actors_app.data.entities.PopularsActorsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ServerGateway {

    @GET("person/popular")
    fun getPopularActors(@Query("page") page: Int): Single<PopularsActorsResponse>

}
