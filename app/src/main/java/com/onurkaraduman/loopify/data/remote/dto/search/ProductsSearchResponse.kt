package com.onurkaraduman.loopify.data.remote.dto.search


import com.google.gson.annotations.SerializedName

data class ProductsSearchResponse(
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("products")
    val productSearches: List<ProductSearch>,
    @SerializedName("skip")
    val skip: Int,
    @SerializedName("total")
    val total: Int
)