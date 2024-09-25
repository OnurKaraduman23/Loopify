package com.onurkaraduman.loopify.ui.screens.detail

import com.onurkaraduman.loopify.domain.model.details.ProductDetailsModel

object DetailContract {
    data class DetailUiState(
        val isLoading: Boolean = false,
        val productDetails: ProductDetailsModel? = null,
        val errorMessage: String? = ""
    )

    sealed class DetailUiAction {
        data object AddToCardClick : DetailUiAction()
        data object AddToFavoriteClick : DetailUiAction() // TODO: Daha sonra class parametresi alan bir class olacak (data class AddFavoriteClick(productDetails: ProductDetailsModel))
    }

    sealed class DetailUiEffect {
        data class ShowToastMessage(val message: String) : DetailUiEffect()
        data object GoToCardScreen : DetailUiEffect()
    }

}
