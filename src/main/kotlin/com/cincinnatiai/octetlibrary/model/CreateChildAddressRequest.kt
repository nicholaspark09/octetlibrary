package com.cincinnatiai.octetlibrary.model

import com.google.gson.annotations.SerializedName

data class CreateChildAddressRequest(
    val name: String,
    val offset: Int,
    @SerializedName("pinHash")
    val pinHash: String,
    @SerializedName("updatePinHash")
    val updatePinHash: String? = null
)