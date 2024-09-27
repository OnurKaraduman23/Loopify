package com.onurkaraduman.loopify.data.mapper

import com.onurkaraduman.loopify.data.remote.dto.category_product.CategoryProductResponse
import com.onurkaraduman.loopify.domain.model.products.ProductsModel


fun CategoryProductResponse.toProductModel(): List<ProductsModel> {
    return this.productCategoryProducts.map {product->
        ProductsModel(
            id = product.id,
            title = product.title,
            price = product.price.toInt(),
            category = product.category,
            image = product.thumbnail
        )
    }
}