package com.dev.mahmoud_ashraf.popular_actors_app.domain.usecases

import com.dev.mahmoud_ashraf.popular_actors_app.data.entities.Actor
import com.dev.mahmoud_ashraf.popular_actors_app.data.entities.PopularsActorsResponse
import com.dev.mahmoud_ashraf.popular_actors_app.domain.repositories.PopularActorsRepository
import io.reactivex.Single

fun fetchPopularActors(
    page: Int?,
    repository: PopularActorsRepository
): Single<List<Actor>> {
    return repository
        .takeIf { page!=null }
        ?.requestPopularActors(page!!)
        ?.map {
            it.peopleList ?: listOf()
        }
        ?: Single.error(Exception())

}
