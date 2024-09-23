package com.onurkaraduman.loopify.data.remote.dto.products


import com.google.gson.annotations.SerializedName

data class Dimensions(
    @SerializedName("depth")
    val depth: Double,
    @SerializedName("height")
    val height: Double,
    @SerializedName("width")
    val width: Double
)