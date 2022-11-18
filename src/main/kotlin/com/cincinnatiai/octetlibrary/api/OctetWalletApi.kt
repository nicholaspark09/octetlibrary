package com.cincinnatiai.octetlibrary.api

import com.cincinnatiai.octetlibrary.model.*
import retrofit2.http.*

interface OctetWalletApi {

    @POST("wallets/{walletId}/child-addresses")
    suspend fun createChildAddress(
        @Path("walletId") walletId: Int,
        @Body request: CreateChildAddressRequest
    ): CreateChildAddressResponse

    @GET("wallets/{walletId}/child-addresses")
    suspend fun fetchChildAddresses(@Path("walletId") walletId: Int): FetchChildAddressResponse

    @PUT("wallets/{walletId}/child-addresses/{address}/pin-hash")
    suspend fun updatePinHash(
        @Path("walletId") walletId: Int, @Path("address") address: String,
        @Body updatePinHashRequest: UpdatePinHashRequest
    ): UpdatePinHashResponse

}