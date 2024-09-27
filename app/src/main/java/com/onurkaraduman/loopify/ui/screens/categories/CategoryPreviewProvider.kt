package com.onurkaraduman.loopify.ui.screens.categories

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.onurkaraduman.loopify.domain.model.categories.CategoriesModel


class CategoryPreviewProvider: PreviewParameterProvider<CategoriesUiState> {
    override val values: Sequence<CategoriesUiState>
        get() = sequenceOf(
            CategoriesUiState(
                isLoading = true,
                errorMessage = null,
                categoriesList = emptyList()
            ),
            CategoriesUiState(
                isLoading = false,
                errorMessage = "Not Found",
                categoriesList = emptyList()
            ),
            CategoriesUiState(
                isLoading = false,
                errorMessage = null,
                categoriesList = listOf(
                    CategoriesModel(
                        name = "Beauty",
                        endPoint = ""
                    ),
                    CategoriesModel(
                        name = "Fragrances",
                        endPoint = ""
                    ),
                    CategoriesModel(
                        name = "Furniture",
                        endPoint = ""
                    ),
                    CategoriesModel(
                        name = "Groceries",
                        endPoint = ""
                    ),
                    CategoriesModel(
                        name = "Home Decoration",
                        endPoint = ""
                    ),
                    CategoriesModel(
                        name = "Kitchen Accessories",
                        endPoint = ""
                    ),
                    CategoriesModel(
                        name = "Laptops",
                        endPoint = ""
                    ),
                    CategoriesModel(
                        name = "Mens Shirts",
                        endPoint = ""
                    ),
                    CategoriesModel(
                        name = "Men Shoes",
                        endPoint = ""
                    ),
                    CategoriesModel(
                        name = "Mens Watches",
                        endPoint = ""
                    ),
                    CategoriesModel(
                        name = "Skin Care",
                        endPoint = ""
                    ),
                    CategoriesModel(
                        name = "Smart Phone",
                        endPoint = ""
                    ),
                    CategoriesModel(
                        name = "Sports Accessories",
                        endPoint = ""
                    ),


                    )
            )
        )
}