package com.imgur.di.navigation

import com.imgur.core_api.NavigationProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [CiceroneModule::class, BottomNavModule::class]
)
interface NavigationComponent : NavigationProvider {

    companion object {
        fun create(): NavigationComponent {
            return DaggerNavigationComponent.builder().build()
        }
    }
}
