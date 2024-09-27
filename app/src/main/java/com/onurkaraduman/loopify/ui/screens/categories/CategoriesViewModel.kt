package com.onurkaraduman.loopify.ui.screens.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onurkaraduman.loopify.common.Resource
import com.onurkaraduman.loopify.domain.use_case.GetCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    private val _categoriesUiState = MutableStateFlow(CategoriesUiState())
    val categoriesUiState: StateFlow<CategoriesUiState> = _categoriesUiState


    init {
        getCategories()
    }

    private fun getCategories() = viewModelScope.launch {
        getCategoriesUseCase.invoke().collect { result ->
            when (result) {
                is Resource.Success -> {
                    updateUiState {
                        copy(
                            isLoading = false,
                            categoriesList = result.data.orEmpty()
                        )
                    }
                }

                is Resource.Loading -> {
                    updateUiState { copy(isLoading = true, categoriesList = result.data.orEmpty()) }
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

    private fun updateUiState(block: CategoriesUiState.() -> CategoriesUiState) {
        _categoriesUiState.update(block)
    }
}