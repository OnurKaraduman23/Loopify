package com.onurkaraduman.loopify.data.remote.dto.category_product


import com.google.gson.annotations.SerializedName

data class MetaCategoryProduct(
    @SerializedName("barcode")
    val barcode: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("qrCode")
    val qrCode: String,
    @SerializedName("updatedAt")
    val updatedAt: String
)