package com.onurkaraduman.loopify.ui.home

import com.onurkaraduman.loopify.domain.model.products.ProductsModel


data class HomeUiState(
    val isLoading: Boolean = false,
    val productList: List<ProductsModel> = emptyList(),
    val errorMessage: String? = ""
)

