package com.onurkaraduman.loopify.data.mapper

import com.onurkaraduman.loopify.data.remote.dto.detail.ProductDetailResponse
import com.onurkaraduman.loopify.domain.model.details.ProductDetailsModel

fun ProductDetailResponse.toProductDetailsModel(): ProductDetailsModel {
    return ProductDetailsModel(
        id = this.id,
        title = this.title,
        descriptions = this.description,
        price = this.price,
        rating = this.rating,
        stock = this.stock,
        imageList = this.images,
        discountPercentage = this.discountPercentage,
        tagList = this.tags,
        reviewList = this.reviewDetails
    )
}