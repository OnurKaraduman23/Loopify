package com.onurkaraduman.loopify.domain.model.details

import com.onurkaraduman.loopify.data.remote.dto.detail.ReviewDetail

data class ProductDetailsModel(
    val id: Int,
    val title: String,
    val descriptions: String,
    val price: Double,
    val rating: Double,
    val stock: Int,
    val imageList: List<String>,
    val discountPercentage: Double,
    val tagList: List<String>,
    val reviewList: List<ReviewDetail>
)
