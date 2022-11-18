package com.cincinnatiai.octetlibrary

import com.cincinnatiai.octetlibrary.api.OctetWalletApi
import com.cincinnatiai.octetlibrary.di.DIModule

class OctetLibrary private constructor(
    private val isDebug: Boolean,
    private val apiToken: String
) {

    private val diModule: DIModule by lazy {
        DIModule.newInstance(isDebug, apiToken)
    }

    val walletApi: OctetWalletApi by lazy {
        diModule.walletApi
    }

    companion object {
        @Volatile
        private var INSTANCE: OctetLibrary? = null

        fun initialize(isDebug: Boolean, apiToken: String) {
            if (INSTANCE == null) {
                synchronized(this) {
                    if (INSTANCE == null) {
                        INSTANCE = OctetLibrary(isDebug, apiToken)
                    }
                }
            }
        }

        fun getInstance() = INSTANCE ?: throw IllegalAccessException("Please call initialize first")
    }
}