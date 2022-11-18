package com.cincinnatiai.octetlibrary.model

import com.google.gson.annotations.SerializedName


data class FetchChildAddressResponse(
    val idx: Int,
    val address: String,
    val name: String,
    @SerializedName("derivationIndex")
    val derivationIndex: Int,
    val type: String,
    val status: String,
    @SerializedName("createdDate")
    val createdDate: String,
    @SerializedName("modifiedDate")
    val modifiedDate: String
)