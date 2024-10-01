package com.onurkaraduman.loopify.ui.screens.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onurkaraduman.loopify.data.local.entity.ProductEntity
import com.onurkaraduman.loopify.domain.use_case.local.LocalProductUseCase
import com.onurkaraduman.loopify.ui.screens.favorites.FavoritesContract.FavoritesUiAction
import com.onurkaraduman.loopify.ui.screens.favorites.FavoritesContract.FavoritesUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val localProductUseCase: LocalProductUseCase
) : ViewModel() {

    private val _favoritesUiState = MutableStateFlow(FavoritesUiState())
    val favoritesUiState = _favoritesUiState


    fun onAction(favoritesUiAction: FavoritesUiAction) {
        when (favoritesUiAction) {
            is FavoritesUiAction.AddToCardClick -> {}
            is FavoritesUiAction.DeleteToFavoriteClick -> {
                viewModelScope.launch {
                    val product = localProductUseCase.selectProduct(favoritesUiAction.id)
                    if (product != null) {
                        deleteProduct(product)
                        getFavorites()
                    }
                }
            }

            is FavoritesUiAction.RefreshFavorites -> {
                getFavorites()
            }
        }

    }

    private fun getFavorites() = viewModelScope.launch(Dispatchers.IO) {
        localProductUseCase.selectProducts().collect { result ->
            updateUiState { copy(productFavorites = result) }
        }

    }

    private suspend fun deleteProduct(product: ProductEntity) {
        localProductUseCase.deleteProduct(product)
    }

    private fun updateUiState(block: FavoritesUiState.() -> FavoritesUiState) {
        _favoritesUiState.update(block)
    }
}