package com.imgur.di.navigation

import com.imgur.core_api.navigation.NavigationProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        CiceroneModule::class,
        NavModule::class
    ]
)
interface NavigationComponent : NavigationProvider {

    @Component.Factory
    interface Factory {
        fun create(): NavigationComponent
    }

    companion object {
        fun create(): NavigationComponent {
            return DaggerNavigationComponent.factory().create()
        }
    }
}
