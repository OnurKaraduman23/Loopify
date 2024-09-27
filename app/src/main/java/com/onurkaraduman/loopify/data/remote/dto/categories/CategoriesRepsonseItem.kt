package com.onurkaraduman.loopify.data.remote.dto.categories


import com.google.gson.annotations.SerializedName

data class CategoriesRepsonseItem(
    @SerializedName("name")
    val name: String,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("url")
    val url: String
)