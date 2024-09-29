package com.onurkaraduman.loopify.ui.screens.category_products

import AppToolbar
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.onurkaraduman.loopify.ui.components.ProductList
import com.onurkaraduman.loopify.ui.screens.category_products.CategoryProductsContract.CategoryProductsUiState
import com.onurkaraduman.loopify.ui.screens.common.ErrorScreen
import com.onurkaraduman.loopify.ui.screens.common.LoadingScreen
import com.onurkaraduman.loopify.ui.screens.main.MainViewModel
import com.onurkaraduman.loopify.ui.screens.main.ToolbarState
import com.onurkaraduman.loopify.ui.theme.LoopifyTheme

@Composable
fun CategoryProductsScreen(
    categoryProductsUiState: CategoryProductsUiState,
    onNavigateDetailScreen: (Int) -> Unit,
    mainViewModel: MainViewModel,
    onBackClickToolbar: () -> Unit
) {
    LaunchedEffect(Unit) {
        mainViewModel.updateToolbarState(ToolbarState(title = "Products", showBackButton = true))
    }

    Scaffold(
        topBar = {
            val toolbarState by mainViewModel.toolbarState.collectAsState()
            AppToolbar(
                toolbarState,
                onBackClick = onBackClickToolbar
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier.fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding())
        ) {

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
}

@Preview(showBackground = true)
@Composable
fun PreviewCaegoryProductScreen(
    @PreviewParameter(CategoryProductsPreviewProvider::class) categoryProductsUiState: CategoryProductsUiState
) {
    LoopifyTheme {
        CategoryProductsScreen(
            categoryProductsUiState = categoryProductsUiState,
            onNavigateDetailScreen = {},
            mainViewModel = MainViewModel(),
            onBackClickToolbar = {}
        )
    }
}
