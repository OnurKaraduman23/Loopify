package com.onurkaraduman.loopify.ui.screens.favorites

import com.onurkaraduman.loopify.data.local.entity.ProductEntity

class FavoritesContract {
    data class FavoritesUiState(
        val productFavorites: List<ProductEntity> = emptyList()
    )

    sealed class FavoritesUiAction {
        data object AddToCardClick : FavoritesUiAction()
        data class DeleteToFavoriteClick(
            val id: Int,
            val title: String,
            val price: Int,
            val category: String,
            val image: String
        ) : FavoritesUiAction()

        data object RefreshFavorites : FavoritesUiAction()
    }
}