package com.imgur.network_impl.di

import com.imgur.network_impl.BuildConfig
import com.imgur.network_impl.utils.AuthInterceptor
import com.imgur.network_impl.utils.NetworkApiFactory
import dagger.Module
import dagger.Provides
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
object NetworkServiceModule {

    private const val endPoint: String = "https://api.imgur.com/3/"
    private const val connectionTimeOut: Long = 10L

    private const val clientId: String = "8b08c59efdc2223"
    private const val clientSecret: String = "64e17993025e7db72db8ba92fb696bc8bed53689"

    private const val accessToken: String = "ab7905d3b0b322a31b1cead1d1f7ac65f9dad9bf"
    private const val refreshToken: String = "c07828771b1009115ff2a49e57fbcc5dd195fa1d"

    private const val accountUsername: String = "schnaps1981101"
    private const val accountId: String = "169711337"


    @Singleton
    @Provides
    fun provideImgurService(
        factory: MoshiConverterFactory
    ) = NetworkApiFactory.Builder(endPoint, listOf(factory))
        .interceptors(listOf(AuthInterceptor(accessToken)))
        .isDebug(BuildConfig.DEBUG)
        .connectionTimeOutSeconds(connectionTimeOut)
        .build()
}
