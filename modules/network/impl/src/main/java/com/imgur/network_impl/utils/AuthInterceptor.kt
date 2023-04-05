package com.imgur.network_impl.utils

import com.imgur.core_api.datastore.UserPreferences
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val userPreferences: UserPreferences
) : Interceptor {

    private var token: String? = null
        get() = field ?: runBlocking {
            userPreferences.accessToken.first()?.also {
                field = it
            }
        }

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        return token?.let {
            val requestBuilder = original.newBuilder()
                .addHeader("Authorization", "Bearer $it")
                .build()

            chain.proceed(requestBuilder)
        } ?: run {
            chain.proceed(original)
        }
    }
}
