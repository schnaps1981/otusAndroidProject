package com.imgur.network_factory


import com.imgur.core_api.datastore.UserPreferencesProvider
import com.imgur.network_api.NetworkProvider
import com.imgur.network_impl.di.DaggerNetworkComponent

object NetworkProvidersFactory {

    fun createNetworkBuilder(userPreferencesProvider: UserPreferencesProvider): NetworkProvider {
        return DaggerNetworkComponent.factory().create(userPreferencesProvider)
    }
}
