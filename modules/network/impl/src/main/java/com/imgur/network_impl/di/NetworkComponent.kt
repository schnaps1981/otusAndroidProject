package com.imgur.network_impl.di

import com.imgur.core_api.datastore.UserPreferencesProvider
import com.imgur.network_api.NetworkProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [UserPreferencesProvider::class],
    modules = [
        NetworkServiceModule::class,
        NetworkApiModule::class,
        MoshiConfigModule::class
    ]
)
interface NetworkComponent : NetworkProvider {

    @Component.Factory
    interface Factory {
        fun create(provider: UserPreferencesProvider): NetworkProvider
    }
}
