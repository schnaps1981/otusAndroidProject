package com.imgur.network_factory


import com.imgur.network_api.NetworkProvider
import com.imgur.network_impl.di.DaggerNetworkComponent

object NetworkProvidersFactory {

    fun createNetworkBuilder(): NetworkProvider {
        return DaggerNetworkComponent.builder().build()
    }
}
