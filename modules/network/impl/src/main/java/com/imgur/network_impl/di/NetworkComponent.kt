package com.imgur.network_impl.di

import com.imgur.network_api.NetworkProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkServiceModule::class,
        NetworkApiModule::class,
        MoshiConfigModule::class
    ]
)
interface NetworkComponent : NetworkProvider
