package com.onurkaraduman.loopify.ui.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onurkaraduman.loopify.common.Resource
import com.onurkaraduman.loopify.domain.use_case.GetSearchProductsUseCase
import com.onurkaraduman.loopify.ui.screens.search.SearchContract.SearchUiAction
import com.onurkaraduman.loopify.ui.screens.search.SearchContract.SearchUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchProductsUseCase: GetSearchProductsUseCase
) : ViewModel() {

    private val _searchUiState = MutableStateFlow(SearchUiState())
    val searchUiState: StateFlow<SearchUiState> = _searchUiState.asStateFlow()

    fun onAction(uiAction: SearchUiAction) {
        when (uiAction) {
            is SearchUiAction.ChangeQuery -> {
                updateUiState { copy(searchQuery = uiAction.searchQuery) }
                updateUiState { copy(productList = emptyList()) }
            }

            is SearchUiAction.SearchProducts -> {
                updateUiState { copy(productList = emptyList()) }
                searchProducts()
            }
        }
    }

    private fun searchProducts() = viewModelScope.launch {
        val searchQuery = _searchUiState.value.searchQuery

        if (searchQuery.isNullOrBlank()) {
            updateUiState {
                copy(
                    isLoading = false,
                    productList = emptyList()
                )
            }
            return@launch
        }

        getSearchProductsUseCase(searchQuery).collect { result ->
            when (result) {
                    is Resource.Success -> {
                        updateUiState {
                            copy(
                                isLoading = false,
                                productList = result.data.orEmpty(),

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
                            errorMessage = result.message.toString()
                        )
                    }
                }
            }
        }
    }


    private fun updateUiState(block: SearchUiState.() -> SearchUiState) {
        _searchUiState.update(block)
    }
}
