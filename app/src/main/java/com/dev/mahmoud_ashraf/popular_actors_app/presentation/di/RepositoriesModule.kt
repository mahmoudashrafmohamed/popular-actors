package com.dev.mahmoud_ashraf.popular_actors_app.presentation.di

import com.dev.mahmoud_ashraf.popular_actors_app.data.gateways.ServerGateway
import com.dev.mahmoud_ashraf.popular_actors_app.data.repositories.PopularActorsRepositoryImp
import com.dev.mahmoud_ashraf.popular_actors_app.domain.repositories.PopularActorsRepository
import org.koin.dsl.module

val repositoryModule = module {
    single {
        providePopularActorsRepository(
            get()
        )
    }
}

fun providePopularActorsRepository(serverGateway: ServerGateway): PopularActorsRepository {
    return PopularActorsRepositoryImp(serverGateway)
}
