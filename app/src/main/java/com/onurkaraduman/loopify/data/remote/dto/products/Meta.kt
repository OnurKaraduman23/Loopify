package com.onurkaraduman.loopify.data.remote.dto.products


import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("barcode")
    val barcode: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("qrCode")
    val qrCode: String,
    @SerializedName("updatedAt")
    val updatedAt: String
)