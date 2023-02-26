package com.imgur.network_impl.di

import com.imgur.network_impl.net.NetRequestImpl
import com.imgur.network_api.NetRequest
import dagger.Binds
import dagger.Module

@Module
interface NetworkModule {

    @Binds
    fun bindsNetworkRequest(impl: NetRequestImpl): NetRequest
}
