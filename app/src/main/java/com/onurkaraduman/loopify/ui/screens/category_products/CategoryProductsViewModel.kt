package com.onurkaraduman.loopify.ui.screens.category_products

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onurkaraduman.loopify.common.Resource
import com.onurkaraduman.loopify.domain.use_case.GetCategoriesProductsUseCase
import com.onurkaraduman.loopify.ui.screens.category_products.CategoryProductsContract.CategoryProductsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryProductsViewModel @Inject constructor(
    private val getCategoriesProductsUseCase: GetCategoriesProductsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _categoryProductsUiState = MutableStateFlow(CategoryProductsUiState())
    val categoryProductsUiState = _categoryProductsUiState

    init {
        savedStateHandle.get<String>("endPoint")?.let { endPoint ->
            getCategoryProducts(endPoint)
        }
    }


    private fun getCategoryProducts(endPoint: String) = viewModelScope.launch {
        getCategoriesProductsUseCase.invoke(endPoint).collect { result ->
            when (result) {
                is Resource.Success -> {
                    updateUiSate {
                        copy(
                            isLoading = false,
                            categoryProductList = result.data.orEmpty()
                        )
                    }
                }

                is Resource.Loading -> {
                    updateUiSate { copy(isLoading = true) }
                }

                is Resource.Error -> {
                    updateUiSate {
                        copy(
                            isLoading = false,
                            errorMessage = result.message ?: "An unknown error occurred"
                        )
                    }
                }
            }
        }
    }


    private fun updateUiSate(block: CategoryProductsUiState.() -> CategoryProductsUiState) {
        _categoryProductsUiState.update(block)
    }
}