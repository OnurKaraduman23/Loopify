package com.onurkaraduman.loopify.data.remote.dto.detail


import com.google.gson.annotations.SerializedName

data class MetaDetail(
    @SerializedName("barcode")
    val barcode: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("qrCode")
    val qrCode: String,
    @SerializedName("updatedAt")
    val updatedAt: String
)