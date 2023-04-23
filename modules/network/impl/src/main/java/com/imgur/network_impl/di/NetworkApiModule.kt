package com.imgur.network_impl.di

import com.imgur.network_impl.BuildConfig
import com.imgur.core_api.Constants
import com.imgur.network_api.api.ImgurApi
import com.imgur.network_impl.utils.ImgurRefreshTokenApi
import com.imgur.network_impl.utils.NetworkApiFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
object NetworkApiModule {

    @Provides
    @Singleton
    fun provideImgurApi(networkFactory: NetworkApiFactory): ImgurApi =
        networkFactory.create()

    //It’s impossible to do it through the factory, because needed inject api interface into the auth interceptor
    @Provides
    @Singleton
    @Named("auth")
    fun provideRefreshTokenClient(): OkHttpClient {
        val isDebug = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.BASIC
        }

        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(isDebug))
            .build()
    }

    @Provides
    @Singleton
    fun provideRefreshTokenService(
        @Named("auth") client: OkHttpClient,
        factory: MoshiConverterFactory
    ): ImgurRefreshTokenApi =
        Retrofit.Builder()
            .baseUrl(Constants.endPoint)
            .client(client)
            .addConverterFactory(factory)
            .build()
            .create(ImgurRefreshTokenApi::class.java)
}
