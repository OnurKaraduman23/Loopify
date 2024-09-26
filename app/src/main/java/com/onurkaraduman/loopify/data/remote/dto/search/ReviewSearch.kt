package com.onurkaraduman.loopify.data.remote.dto.search


import com.google.gson.annotations.SerializedName

data class ReviewSearch(
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