package com.onurkaraduman.loopify.ui.screens.search

import com.onurkaraduman.loopify.domain.model.products.ProductsModel

object SearchContract {
    data class SearchUiState(
        val isLoading: Boolean = false,
        val searchQuery: String? = null,
        val errorMessage: String? = null,
        val productList: List<ProductsModel> = emptyList(),
        val isSearching: Boolean = false
    )

    sealed class SearchUiAction {
        data class ChangeQuery(val searchQuery: String) : SearchUiAction()
        data object SearchProducts: SearchUiAction()
        data object RetryErrorScreenClick : SearchUiAction()
    }

}