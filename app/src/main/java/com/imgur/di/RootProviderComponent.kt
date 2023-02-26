package com.imgur.di

import android.app.Application
import com.imgur.core_api.AppProvider
import com.imgur.core_api.RootProvider
import com.imgur.database_api.DatabaseProvider
import com.imgur.database_factory.DatabaseProvidersFactory
import com.imgur.network_api.NetworkProvider
import com.imgur.network_factory.NetworkProvidersFactory
import dagger.Component

@Component(
    dependencies = [
        AppProvider::class,
        NetworkProvider::class,
        DatabaseProvider::class
    ]
)
interface RootProviderComponent : RootProvider {

    companion object {
        fun init(application: Application): RootProviderComponent {

            val appProvider = AppComponent.create(application)
            val networkProvider = NetworkProvidersFactory.createNetworkBuilder()
            val databaseProvider = DatabaseProvidersFactory.createDatabaseBuilder(application)


            return DaggerRootProviderComponent.builder()
                .appProvider(appProvider)
                .networkProvider(networkProvider)
                .databaseProvider(databaseProvider)
                .build()
        }

    }
}