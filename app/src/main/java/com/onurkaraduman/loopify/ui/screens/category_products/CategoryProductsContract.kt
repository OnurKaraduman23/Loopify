package com.onurkaraduman.loopify.ui.screens.category_products

import com.onurkaraduman.loopify.domain.model.products.ProductsModel

object CategoryProductsContract {
    data class CategoryProductsUiState(
        val isLoading: Boolean = false,
        val categoryProductList: List<ProductsModel> = emptyList(),
        val errorMessage: String? = ""
    )

}