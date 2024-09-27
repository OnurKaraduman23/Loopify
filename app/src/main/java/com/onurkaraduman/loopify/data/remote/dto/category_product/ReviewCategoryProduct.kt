package com.onurkaraduman.loopify.data.remote.dto.category_product


import com.google.gson.annotations.SerializedName

data class ReviewCategoryProduct(
    @SerializedName("comment")
    val comment: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("rating")
    val rating: Int,
    @SerializedName("reviewerEmail")
    val reviewerEmail: String,
    @SerializedName("reviewerName")
    val reviewerName: String
)