package com.onurkaraduman.loopify.ui.screens.category_products

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.onurkaraduman.loopify.domain.model.products.ProductsModel
import com.onurkaraduman.loopify.ui.screens.category_products.CategoryProductsContract.CategoryProductsUiState

class CategoryProductsPreviewProvider: PreviewParameterProvider<CategoryProductsUiState> {
    override val values: Sequence<CategoryProductsUiState>
        get() = sequenceOf(
            CategoryProductsUiState(
                isLoading = true,
                errorMessage = null,
                categoryProductList = emptyList()
            ),
            CategoryProductsUiState(
                isLoading = false,
                errorMessage = "Not Found",
                categoryProductList = emptyList()
            ),
            CategoryProductsUiState(
                isLoading = false,
                errorMessage = null,
                categoryProductList = listOf(
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