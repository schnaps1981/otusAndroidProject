package com.imgur.di

import android.app.Application
import com.imgur.core_api.AppProvider
import com.imgur.core_api.RootProvider
import com.imgur.core_api.datastore.UserPreferencesProvider
import com.imgur.core_api.navigation.NavigationProvider
import com.imgur.core_factory.CoreProvidersFactory
import com.imgur.di.navigation.NavigationComponent
import dagger.Component

@Component(
    dependencies = [
        AppProvider::class,
        NavigationProvider::class,
        UserPreferencesProvider::class
    ]
)
interface RootProviderComponent : RootProvider {

    companion object {
        fun init(application: Application): RootProviderComponent {

            val appProvider = AppComponent.create(application)

            val navProvider = NavigationComponent.create()

            val userPreferencesProvider =
                CoreProvidersFactory.createUserPreferencesProvider(appProvider)

            return DaggerRootProviderComponent.builder()
                .appProvider(appProvider)
                .navigationProvider(navProvider)
                .userPreferencesProvider(userPreferencesProvider)
                .build()
        }
    }
}
