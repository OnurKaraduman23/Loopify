package com.onurkaraduman.loopify.data.mapper

import com.onurkaraduman.loopify.data.remote.dto.search.ProductsSearchResponse
import com.onurkaraduman.loopify.domain.model.products.ProductsModel

fun ProductsSearchResponse.toProductModelList(): List<ProductsModel> {
    return this.productSearches.map { product ->
        ProductsModel(
            id = product.id,
            title = product.title,
            price = product.price.toInt(),
            category = product.category,
            image = product.thumbnail
        )
    }
}
