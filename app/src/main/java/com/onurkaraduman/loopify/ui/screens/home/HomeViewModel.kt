package com.onurkaraduman.loopify.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onurkaraduman.loopify.common.Resource
import com.onurkaraduman.loopify.domain.use_case.GetAllProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.onurkaraduman.loopify.ui.screens.home.HomeContract.HomeUiAction
import com.onurkaraduman.loopify.ui.screens.home.HomeContract.HomeUiState


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllProductsUseCase: GetAllProductsUseCase
) : ViewModel() {
    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState: StateFlow<HomeUiState> = _homeUiState


    init {
        getAllProducts()
    }

    fun onAction(uiAction: HomeUiAction){
        when(uiAction){
            // başka actionlar ekelenebilir when case'i ondan dolayı duruyor
            is HomeUiAction.RetryErrorScreenClick -> retryFetchingProducts()
        }
    }

    private fun getAllProducts() = viewModelScope.launch {
        getAllProductsUseCase.invoke().collect { result ->
            when (result) {
                is Resource.Success -> {
                    updateUiState { copy(isLoading = false, productList = result.data.orEmpty()) }
                }

                is Resource.Loading -> {
                    updateUiState { copy(isLoading = true) }
                }

                is Resource.Error -> {
                    updateUiState {
                        copy(
                            isLoading = false,
                            errorMessage = result.message ?: "An unknown error Occurred"
                        )
                    }

                }

            }

        }

    }

    fun retryFetchingProducts() {
        updateUiState { copy(errorMessage = null) }
        getAllProducts()
    }

    private fun updateUiState(block: HomeUiState.() -> HomeUiState) {
        _homeUiState.update(block)
    }


}