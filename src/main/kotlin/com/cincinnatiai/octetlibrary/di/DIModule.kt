package com.cincinnatiai.octetlibrary.di

import com.cincinnatiai.octetlibrary.api.OctetWalletApi
import com.cincinnatiai.octetlibrary.interceptor.InterceptorProvider
import com.cincinnatiai.octetlibrary.interceptor.InterceptorProviderContract
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DIModule private constructor(
    private val isDebug: Boolean,
    private val apiToken: String
) {

    private val baseUrl = "https://octet-api.blockchainapi.io/2.0/"

    private val interceptorProvider: InterceptorProviderContract by lazy {
        InterceptorProvider(isDebug, apiToken)
    }

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(interceptorProvider.provideAccessInterceptor())
            .addInterceptor(interceptorProvider.provideLoggingInterceptor())
            .build()
    }

    private val walletRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val walletApi: OctetWalletApi by lazy {
        walletRetrofit.create(OctetWalletApi::class.java)
    }

    companion object {

        @Synchronized
        fun newInstance(isDebug: Boolean, apiToken: String) = DIModule(isDebug, apiToken)
    }

}