package com.onurkaraduman.loopify.data.mapper

import com.onurkaraduman.loopify.data.remote.dto.products.ProductsResponse
import com.onurkaraduman.loopify.domain.model.products.ProductsModel

fun ProductsResponse.toProductsModelList(): List<ProductsModel> {
    return this.products.map { product ->
        ProductsModel(
            id = product.id,
            title = product.title,
            price = product.price.toInt(),
            rating = product.rating.toInt(),
            category = product.category,
            image = product.thumbnail,
            tags = product.tags
        )
    }
}