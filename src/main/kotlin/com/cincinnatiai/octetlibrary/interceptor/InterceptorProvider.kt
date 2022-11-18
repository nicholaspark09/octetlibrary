package com.cincinnatiai.octetlibrary.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor

interface InterceptorProviderContract {
    fun provideAccessInterceptor(): Interceptor

    fun provideLoggingInterceptor(): Interceptor
}
class InterceptorProvider(
    private val isDebug: Boolean,
    private val apiToken: String
) : InterceptorProviderContract {

    override fun provideAccessInterceptor(): Interceptor = Interceptor {
        val request = it.request()
        it.proceed(newRequestWithAccessToken(apiToken, request))
    }

    override fun provideLoggingInterceptor(): Interceptor = HttpLoggingInterceptor().apply {
        if (isDebug) level = HttpLoggingInterceptor.Level.BODY
        else level = HttpLoggingInterceptor.Level.NONE
    }

    private fun newRequestWithAccessToken(accessToken: String?, request: Request): Request =
        request.newBuilder()
            .header("Authorization", "Bearer $accessToken")
            .build()
}