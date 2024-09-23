package com.onurkaraduman.loopify.ui.home.components

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.onurkaraduman.loopify.domain.model.products.ProductsModel
import com.onurkaraduman.loopify.ui.home.HomeUiState

class HomeScreenPreviewProvider: PreviewParameterProvider<HomeUiState> {
    override val values: Sequence<HomeUiState>
        get() = sequenceOf(
            HomeUiState(
                isLoading = true,
                errorMessage = null,
                productList = emptyList()
            ),
            HomeUiState(
                isLoading = false,
                errorMessage = "Not Found",
                productList = emptyList()
            ),
            HomeUiState(
                isLoading = false,
                errorMessage = null,
                productList = listOf(
                    ProductsModel(
                        id = 1,
                        title = "Huawei Watch GT4 Pro Huawei Watch GT4 Pro Huawei Watch",
                        price = 1299,
                        category = "Accessories",
                        tags = listOf("Accessories", "Watch"),
                        image = "https://www.apple.com/v/watch/bo/images/overview/select/product_se__frx4hb13romm_xlarge.png",
                        rating = 4
                    ),
                    ProductsModel(
                        id = 2,
                        title = "Eyeshadow Palette with Mirror",
                        price = 89,
                        category = "Accessories",
                        tags = listOf("Accessories", "Watch"),
                        image = "https://www.apple.com/v/watch/bo/images/overview/select/product_se__frx4hb13romm_xlarge.png",
                        rating = 2
                    ),
                    ProductsModel(
                        id = 3,
                        title = "Powder Canister",
                        price = 129,
                        category = "Accessories",
                        tags = listOf("Accessories", "Watch"),
                        image = "https://www.apple.com/v/watch/bo/images/overview/select/product_se__frx4hb13romm_xlarge.png",
                        rating = 5
                    ),
                    ProductsModel(
                        id = 4,
                        title = "Red Nail Polish",
                        price = 1999,
                        category = "Accessories",
                        tags = listOf("Accessories", "Watch"),
                        image = "https://www.apple.com/v/watch/bo/images/overview/select/product_se__frx4hb13romm_xlarge.png",
                        rating = 4
                    ),

                )
            )
        )
}