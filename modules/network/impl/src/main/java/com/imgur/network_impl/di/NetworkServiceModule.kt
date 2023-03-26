package com.imgur.network_impl.di

import com.imgur.network_impl.BuildConfig
import com.imgur.network_impl.net.NetworkApiFactory
import dagger.Module
import dagger.Provides
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
object NetworkServiceModule {

    private const val endPoint: String = "https://api.imgur.com/3/"
    private const val connectionTimeOut: Long = 10L

    private const val clientId: String = "be211acc3d77daa"
    private const val clientSecret: String = "fd2f09f8453c8455d74c0d94018064cb0e5b9b94"


    @Singleton
    @Provides
    fun provideImgurService(
        factory: MoshiConverterFactory
    ) = NetworkApiFactory.Builder(endPoint, listOf(factory))
        .isDebug(BuildConfig.DEBUG)
        .connectionTimeOutSeconds(connectionTimeOut)
        .build()
}
