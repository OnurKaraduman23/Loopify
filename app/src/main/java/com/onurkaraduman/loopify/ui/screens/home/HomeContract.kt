package com.onurkaraduman.loopify.ui.screens.home

import com.onurkaraduman.loopify.domain.model.products.ProductsModel

object HomeContract{
    data class HomeUiState(
        val isLoading: Boolean = false,
        val productList: List<ProductsModel> = emptyList(),
        val errorMessage: String? = ""
    )

    sealed class HomeUiAction {
        data object RetryErrorScreenClick : HomeUiAction()
    }

}






