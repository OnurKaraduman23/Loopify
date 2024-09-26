package com.onurkaraduman.loopify.ui.screens.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onurkaraduman.loopify.common.Resource
import com.onurkaraduman.loopify.domain.use_case.GetProductDetailUseCase
import com.onurkaraduman.loopify.ui.screens.detail.DetailContract.DetailUiAction
import com.onurkaraduman.loopify.ui.screens.detail.DetailContract.DetailUiEffect
import com.onurkaraduman.loopify.ui.screens.detail.DetailContract.DetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getProductDetailUseCase: GetProductDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _detailUiState = MutableStateFlow(DetailUiState())
    val detailUiState: StateFlow<DetailUiState> = _detailUiState

    private val _uiEffect by lazy { Channel<DetailUiEffect>() }
    val uiEffect: Flow<DetailUiEffect> by lazy { _uiEffect.receiveAsFlow() }


    init {
        savedStateHandle.get<Int>("id")?.let { id ->
            getProductDetails(id)
        }
    }

    fun onAction(detailUiAction: DetailUiAction) {
        when (detailUiAction) {
            is DetailUiAction.AddToCardClick -> addToCard()
            is DetailUiAction.AddToFavoriteClick -> addToFavorites()
        }
    }


    private fun getProductDetails(id: Int) = viewModelScope.launch {
        getProductDetailUseCase.invoke(id).collect { result ->
            when (result) {
                is Resource.Success -> {
                    updateUiState {
                        copy(
                            isLoading = false,
                            productDetails = result.data
                        )
                    }
                }

                is Resource.Loading -> {
                    updateUiState { copy(isLoading = true) }
                }

                is Resource.Error -> {
                    updateUiState {
                        copy(
                            isLoading = false,
                            errorMessage = result.message
                        )
                    }
                }
            }
        }
    }


    private fun addToCard() = viewModelScope.launch {
        emitUiEffect(DetailUiEffect.ShowToastMessage("In Progress (Add To Card Button)"))
    }

    private fun addToFavorites() = viewModelScope.launch {
        emitUiEffect(DetailUiEffect.ShowToastMessage("In Progress (Add Favorite Icon Button)"))
    }


    private suspend fun emitUiEffect(uiEffect: DetailUiEffect) {
        _uiEffect.send(uiEffect)
    }

    private fun updateUiState(block: DetailUiState.() -> DetailUiState) {
        _detailUiState.update(block)
    }

}