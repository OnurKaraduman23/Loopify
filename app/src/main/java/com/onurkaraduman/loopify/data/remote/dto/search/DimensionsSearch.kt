package com.onurkaraduman.loopify.data.remote.dto.search


import com.google.gson.annotations.SerializedName

data class DimensionsSearch(
    @SerializedName("depth")
    val depth: Double,
    @SerializedName("height")
    val height: Double,
    @SerializedName("width")
    val width: Double
)