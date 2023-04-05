package com.imgur.network_impl.di

import com.imgur.core_api.Constants
import com.imgur.core_api.datastore.UserPreferences
import com.imgur.network_impl.BuildConfig
import com.imgur.network_impl.utils.AuthInterceptor
import com.imgur.network_impl.utils.NetworkApiFactory
import dagger.Module
import dagger.Provides
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
object NetworkServiceModule {
    @Singleton
    @Provides
    fun provideAuthInterceptor(
        userPreferences: UserPreferences
    ) = AuthInterceptor(
        userPreferences
    )

    @Singleton
    @Provides
    fun provideImgurService(
        factory: MoshiConverterFactory,
        authInterceptor: AuthInterceptor
    ) = NetworkApiFactory.Builder(Constants.endPoint, listOf(factory))
        .interceptors(listOf(authInterceptor))
        .isDebug(BuildConfig.DEBUG)
        .connectionTimeOutSeconds(Constants.okHttpConnectionTimeOut)
        .build()
}
