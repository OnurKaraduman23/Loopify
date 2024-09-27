package com.onurkaraduman.loopify.data.remote.dto.category_product


import com.google.gson.annotations.SerializedName

data class DimensionsCategoryProduct(
    @SerializedName("depth")
    val depth: Double,
    @SerializedName("height")
    val height: Double,
    @SerializedName("width")
    val width: Double
)