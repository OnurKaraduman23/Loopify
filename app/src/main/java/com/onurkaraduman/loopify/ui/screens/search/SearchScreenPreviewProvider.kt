package com.onurkaraduman.loopify.ui.screens.search

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.onurkaraduman.loopify.domain.model.products.ProductsModel
import com.onurkaraduman.loopify.ui.screens.search.SearchContract.SearchUiState

class SearchScreenPreviewProvider: PreviewParameterProvider<SearchUiState> {
    override val values: Sequence<SearchUiState>
        get() = sequenceOf(
            SearchUiState(
                isLoading = true,
                errorMessage = null,
                productList = emptyList()
            ),
            SearchUiState(
                isLoading = false,
                errorMessage = "Not Found",
                productList = emptyList()
            ),
            SearchUiState(
                isLoading = false,
                errorMessage = null,
                productList = listOf(
                    ProductsModel(
                        id = 1,
                        title = "Huawei Watch GT4 Pro Huawei Watch GT4 Pro Huawei Watch",
                        price = 1299,
                        category = "Accessories",
                        image = "https://www.apple.com/v/watch/bo/images/overview/select/product_se__frx4hb13romm_xlarge.png",
                    ),
                    ProductsModel(
                        id = 2,
                        title = "Eyeshadow Palette with Mirror",
                        price = 89,
                        category = "Accessories",
                        image = "https://www.apple.com/v/watch/bo/images/overview/select/product_se__frx4hb13romm_xlarge.png",
                    ),
                    ProductsModel(
                        id = 3,
                        title = "Powder Canister",
                        price = 129,
                        category = "Accessories",
                        image = "https://www.apple.com/v/watch/bo/images/overview/select/product_se__frx4hb13romm_xlarge.png",
                    ),
                    ProductsModel(
                        id = 4,
                        title = "Red Nail Polish",
                        price = 1999,
                        category = "Accessories",
                        image = "https://www.apple.com/v/watch/bo/images/overview/select/product_se__frx4hb13romm_xlarge.png",
                    ),

                )
            )
        )
}