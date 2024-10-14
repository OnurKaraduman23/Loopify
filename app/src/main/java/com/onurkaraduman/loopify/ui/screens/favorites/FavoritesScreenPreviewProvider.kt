package com.onurkaraduman.loopify.ui.screens.favorites

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.onurkaraduman.loopify.data.local.entity.ProductEntity
import com.onurkaraduman.loopify.ui.screens.favorites.FavoritesContract.FavoritesUiState

class FavoritesScreenPreviewProvider: PreviewParameterProvider<FavoritesUiState> {
    override val values: Sequence<FavoritesUiState>
        get() = sequenceOf(

            FavoritesUiState(
                productFavorites =  emptyList()
            ),
            FavoritesUiState(

                productFavorites = listOf(
                    ProductEntity(
                        id = 1,
                        title = "Huawei Watch GT4 Pro Huawei Watch GT4 Pro Huawei Watch",
                        price = 1299,
                        category = "Accessories",
                        image = "https://www.apple.com/v/watch/bo/images/overview/select/product_se__frx4hb13romm_xlarge.png",
                    ),
                    ProductEntity(
                        id = 2,
                        title = "Eyeshadow Palette with Mirror",
                        price = 89,
                        category = "Accessories",
                        image = "https://www.apple.com/v/watch/bo/images/overview/select/product_se__frx4hb13romm_xlarge.png",
                    ),
                    ProductEntity(
                        id = 3,
                        title = "Powder Canister",
                        price = 129,
                        category = "Accessories",
                        image = "https://www.apple.com/v/watch/bo/images/overview/select/product_se__frx4hb13romm_xlarge.png",
                    ),
                    ProductEntity(
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