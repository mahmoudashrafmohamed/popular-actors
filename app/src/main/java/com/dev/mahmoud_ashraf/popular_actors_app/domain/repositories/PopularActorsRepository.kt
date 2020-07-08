package com.dev.mahmoud_ashraf.popular_actors_app.domain.repositories

import com.dev.mahmoud_ashraf.popular_actors_app.data.entities.PopularsActorsResponse
import io.reactivex.Single

interface PopularActorsRepository {

    fun requestPopularActors(page: Int): Single<PopularsActorsResponse>

}