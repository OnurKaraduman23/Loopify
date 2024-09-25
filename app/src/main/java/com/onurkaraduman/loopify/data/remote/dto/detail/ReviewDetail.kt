package com.onurkaraduman.loopify.data.remote.dto.detail


import com.google.gson.annotations.SerializedName

data class ReviewDetail(
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