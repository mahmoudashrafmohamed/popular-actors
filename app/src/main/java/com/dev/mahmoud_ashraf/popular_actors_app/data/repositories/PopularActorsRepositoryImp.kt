package com.dev.mahmoud_ashraf.popular_actors_app.data.repositories

import com.dev.mahmoud_ashraf.popular_actors_app.data.entities.PopularsActorsResponse
import com.dev.mahmoud_ashraf.popular_actors_app.data.gateways.ServerGateway
import com.dev.mahmoud_ashraf.popular_actors_app.domain.repositories.PopularActorsRepository
import io.reactivex.Single

class PopularActorsRepositoryImp(private val serverGateway: ServerGateway) : PopularActorsRepository {

    override fun requestPopularActors(page: Int): Single<PopularsActorsResponse> {
        return serverGateway.getPopularActors(page = page)
    }

}