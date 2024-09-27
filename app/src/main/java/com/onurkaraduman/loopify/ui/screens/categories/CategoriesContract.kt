package com.onurkaraduman.loopify.ui.screens.categories

import com.onurkaraduman.loopify.domain.model.categories.CategoriesModel

data class CategoriesUiState(
    val isLoading: Boolean = false,
    val categoriesList: List<CategoriesModel> = emptyList(),
    val errorMessage: String? = ""
)