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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.onurkaraduman.loopify.ui.components.ProductList
import com.onurkaraduman.loopify.ui.screens.category_products.CategoryProductsContract.CategoryProductsUiState
import com.onurkaraduman.loopify.ui.screens.common.ErrorScreen
import com.onurkaraduman.loopify.ui.screens.common.LoadingScreen
import com.onurkaraduman.loopify.ui.screens.main.MainContract.MainUiAction
import com.onurkaraduman.loopify.ui.screens.main.MainContract.MainUiEffect
import com.onurkaraduman.loopify.ui.screens.main.MainContract.ToolbarState
import com.onurkaraduman.loopify.ui.theme.LoopifyTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Composable
fun CategoryProductsScreen(
    categoryProductsUiState: CategoryProductsUiState,
    onNavigateDetailScreen: (Int) -> Unit,
    onToolbarAction: (MainUiAction) -> Unit,
    toolbarUiEffect: Flow<MainUiEffect>,
    onBackClickToolbar: () -> Unit,
    onNavigateCart: () -> Unit
) {
    val toolbarState = remember { mutableStateOf(ToolbarState()) }

    LaunchedEffect(toolbarUiEffect) {
        toolbarUiEffect.collect { effect ->
            when (effect) {
                is MainUiEffect.SetTitle -> {
                    toolbarState.value = toolbarState.value.copy(title = "Products", showBackButton = true)
                }

            }
        }

    }

    LaunchedEffect(Unit) {
        onToolbarAction(MainUiAction.FetchToolbar)
    }

    Scaffold(
        topBar = {
            AppToolbar(
                toolbarState = toolbarState.value,
                onBackClick = onBackClickToolbar,
                onCartClick = onNavigateCart
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
            onBackClickToolbar = {},
            onNavigateCart = {},
            onToolbarAction = {},
            toolbarUiEffect = flow {  }
        )
    }
}
