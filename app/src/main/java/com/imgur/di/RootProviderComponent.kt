package com.imgur.di

import android.app.Application
import com.imgur.core_api.AppProvider
import com.imgur.core_api.RootProvider
import com.imgur.core_api.navigation.NavigationProvider
import com.imgur.di.navigation.NavigationComponent
import dagger.Component

@Component(
    dependencies = [AppProvider::class, NavigationProvider::class]
)
interface RootProviderComponent : RootProvider {

    companion object {
        fun init(application: Application): RootProviderComponent {

            val appProvider = AppComponent.create(application)

            val navProvider = NavigationComponent.create()

            return DaggerRootProviderComponent.builder()
                .appProvider(appProvider)
                .navigationProvider(navProvider)
                .build()
        }
    }
}
