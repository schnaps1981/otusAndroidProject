package com.imgur.network_impl.di

import com.imgur.network_impl.net.ImgurApi
import com.imgur.network_impl.net.NetworkApiFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object NetworkApiModule {

    @Provides
    @Singleton
    fun provideImgurApi(networkFactory: NetworkApiFactory): ImgurApi = networkFactory.create()
}