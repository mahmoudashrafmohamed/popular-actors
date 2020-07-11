package com.dev.mahmoud_ashraf.popular_actors_app.data.repositories

import com.dev.mahmoud_ashraf.popular_actors_app.data.entities.PopularsActorsResponse
import com.dev.mahmoud_ashraf.popular_actors_app.data.gateways.ServerGateway
import com.dev.mahmoud_ashraf.popular_actors_app.domain.repositories.PopularActorsRepository
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.ResponseBody

class PopularActorsRepositoryImp(private val serverGateway: ServerGateway) : PopularActorsRepository {

    override fun requestPopularActors(page: Int?): Single<PopularsActorsResponse> {
        return serverGateway.getPopularActors(page = page!!)
    }

    override fun downloadImage(url: String): Observable<ResponseBody> {
            return serverGateway.downloadImage(url)
    }

}