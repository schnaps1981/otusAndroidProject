package com.imgur.network_impl.di

import com.imgur.core_api.Constants
import com.imgur.core_api.datastore.UserPreferences
import com.imgur.network_impl.BuildConfig
import com.imgur.network_impl.utils.AuthAuthenticator
import com.imgur.network_impl.utils.AuthInterceptor
import com.imgur.network_impl.utils.ImgurRefreshTokenApi
import com.imgur.network_impl.utils.NetworkApiFactory
import dagger.Module
import dagger.Provides
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
object NetworkServiceModule {
    @Singleton
    @Provides
    fun provideAuthInterceptor(userPreferences: UserPreferences) = AuthInterceptor(userPreferences)

    fun provideAuthenticator(
        userPreferences: UserPreferences,
        imgurRefreshTokenApi: ImgurRefreshTokenApi
    ) = AuthAuthenticator(
        userPreferences,
        imgurRefreshTokenApi
    )

    @Singleton
    @Provides
    fun provideImgurService(
        factory: MoshiConverterFactory,
        authInterceptor: AuthInterceptor,
        authAuthenticator: AuthAuthenticator
    ) = NetworkApiFactory.Builder(Constants.endPoint, listOf(factory))
        .interceptors(listOf(authInterceptor))
        .authenticator(authAuthenticator)
        .isDebug(BuildConfig.DEBUG)
        .connectionTimeOutSeconds(Constants.okHttpConnectionTimeOut)
        .build()
}
