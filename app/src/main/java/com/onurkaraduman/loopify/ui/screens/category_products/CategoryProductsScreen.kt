package com.onurkaraduman.loopify.ui.screens.category_products

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.onurkaraduman.loopify.ui.components.ProductList
import com.onurkaraduman.loopify.ui.screens.category_products.CategoryProductsContract.CategoryProductsUiState
import com.onurkaraduman.loopify.ui.screens.common.ErrorScreen
import com.onurkaraduman.loopify.ui.screens.common.LoadingScreen
import com.onurkaraduman.loopify.ui.theme.LoopifyTheme

@Composable
fun CategoryProductsScreen(
    categoryProductsUiState: CategoryProductsUiState,
    onNavigateDetailScreen: (Int) -> Unit
) {

    Column(modifier = Modifier.fillMaxSize()) {

        Spacer(modifier = Modifier.height(8.dp))
        when {
            categoryProductsUiState.isLoading -> {
                LoadingScreen()
            }

            categoryProductsUiState.categoryProductList.isNotEmpty() -> {
                ProductList(
                    products = categoryProductsUiState.categoryProductList,
                    onClick = { onNavigateDetailScreen(it) })

            }

            categoryProductsUiState.errorMessage != null -> {
                ErrorScreen(message = categoryProductsUiState.errorMessage, onClick = {})
            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun PreviewCaegoryProductScreen(
    @PreviewParameter(CategoryProductsPreviewProvider::class) categoryProductsUiState: CategoryProductsUiState
) {
    LoopifyTheme {
        CategoryProductsScreen(
            categoryProductsUiState = categoryProductsUiState,
            onNavigateDetailScreen = {}
        )
    }
}
