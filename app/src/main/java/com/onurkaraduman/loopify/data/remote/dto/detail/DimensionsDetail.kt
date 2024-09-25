package com.onurkaraduman.loopify.data.remote.dto.detail


import com.google.gson.annotations.SerializedName

data class DimensionsDetail(
    @SerializedName("depth")
    val depth: Double,
    @SerializedName("height")
    val height: Double,
    @SerializedName("width")
    val width: Double
)