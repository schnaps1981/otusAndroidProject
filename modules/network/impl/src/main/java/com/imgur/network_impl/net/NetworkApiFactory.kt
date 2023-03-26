package com.imgur.network_impl.net

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

class NetworkApiFactory private constructor(private val builder: Builder) {

    data class Builder(val endPoint: String, val factory: List<Converter.Factory>) {

        var isDebug: Boolean = true
            private set

        var connectionTimeOutSeconds: Long = 10
            private set

        var interceptors: List<Interceptor> = mutableListOf()
            private set

        fun isDebug(isDebug: Boolean) = apply { this.isDebug = isDebug }

        fun connectionTimeOutSeconds(timeOutSec: Long) =
            apply { this.connectionTimeOutSeconds = timeOutSec }

        fun interceptors(interceptors: List<Interceptor>) =
            apply { this.interceptors = interceptors }

        fun build(): NetworkApiFactory = NetworkApiFactory(this)
    }

    inline fun <reified T> create(): T = retrofit.create(T::class.java)

    val retrofit: Retrofit
        get() {
            val retrofitBuilder = Retrofit.Builder()
                .baseUrl(builder.endPoint)
            builder.factory.forEach {
                retrofitBuilder.addConverterFactory(it)
            }

            return retrofitBuilder
                .client(client)
                .build()
        }

    private val client: OkHttpClient
        get() {
            val logging = HttpLoggingInterceptor()
            logging.level = if (builder.isDebug)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.BASIC

            val okHttp = OkHttpClient.Builder()
            okHttp.addInterceptor(logging)

            builder.interceptors.forEach { okHttp.addInterceptor(it) }

            okHttp.connectTimeout(builder.connectionTimeOutSeconds, TimeUnit.SECONDS)
                .readTimeout(builder.connectionTimeOutSeconds, TimeUnit.SECONDS)
                .writeTimeout(builder.connectionTimeOutSeconds, TimeUnit.SECONDS)

            return okHttp.build()
        }
}
