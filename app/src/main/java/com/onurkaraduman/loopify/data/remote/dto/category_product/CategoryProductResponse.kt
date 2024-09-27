package com.onurkaraduman.loopify.data.remote.dto.category_product


import com.google.gson.annotations.SerializedName

data class CategoryProductResponse(
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("products")
    val productCategoryProducts: List<ProductCategoryProduct>,
    @SerializedName("skip")
    val skip: Int,
    @SerializedName("total")
    val total: Int
)