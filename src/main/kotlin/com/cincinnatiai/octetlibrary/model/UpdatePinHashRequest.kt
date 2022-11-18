package com.cincinnatiai.octetlibrary.model

import com.google.gson.annotations.SerializedName

data class UpdatePinHashRequest(
    @SerializedName("pinHash")
    val pinHash: String,
    @SerializedName("updatePinHash")
    val updatePinHash: String
)