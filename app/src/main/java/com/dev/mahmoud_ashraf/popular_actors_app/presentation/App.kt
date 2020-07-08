package com.dev.mahmoud_ashraf.popular_actors_app.presentation

import android.app.Application
import com.dev.mahmoud_ashraf.popular_actors_app.BuildConfig
import com.dev.mahmoud_ashraf.popular_actors_app.presentation.di.networkModule
import com.dev.mahmoud_ashraf.popular_actors_app.presentation.di.repositoryModule
import com.dev.mahmoud_ashraf.popular_actors_app.presentation.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        setupKoin()
        setupTimber()
    }

    private fun setupKoin() {
        startKoin {
            // Koin logger
            androidLogger()
            // inject Android context
            androidContext(this@App)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}