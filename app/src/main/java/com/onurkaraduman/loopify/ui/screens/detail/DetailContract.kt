package com.onurkaraduman.loopify.ui.screens.detail

import com.onurkaraduman.loopify.domain.model.details.ProductDetailsModel

object DetailContract {
    data class DetailUiState(
        val isLoading: Boolean = false,
        val productDetails: ProductDetailsModel? = null,
        val errorMessage: String? = "",
        val isFavorite: Boolean = false
    )

    sealed class DetailUiAction {
        data object AddToCardClick : DetailUiAction()
        data class AddToFavoriteClick(
            val id: Int,
            val title: String,
            val price: Int,
            val category: String,
            val image: String
        ) : DetailUiAction()
    }

    sealed class DetailUiEffect {
        data class ShowToastMessage(val message: String) : DetailUiEffect()
        data object GoToCardScreen : DetailUiEffect()
    }

}
