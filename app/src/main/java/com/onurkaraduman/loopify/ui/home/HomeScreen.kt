package com.onurkaraduman.loopify.ui.home

import ProductCard
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.onurkaraduman.loopify.domain.model.products.ProductsModel
import com.onurkaraduman.loopify.ui.common.EmptyScreen
import com.onurkaraduman.loopify.ui.theme.LoopifyTheme


@Composable
fun HomeScreen(
    homeUiState: HomeUiState,
    onNavigateDetailScreen: () -> Unit
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when {
            homeUiState.isLoading -> CircularProgressIndicator()
            homeUiState.productList.isNotEmpty() -> ProductList(
                homeUiState.productList,
                onClick = { onNavigateDetailScreen() })

            else -> EmptyScreen(homeUiState.errorMessage.toString())
        }
    }

}

@Composable
fun ProductList(
    products: List<ProductsModel>,
    onClick: () -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(products) { product ->
            ProductCard(
                productList = product,
                onClick = { onClick() }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun previewHomeScreen(
    @PreviewParameter(HomeScreenPreviewProvider::class) homeUiState: HomeUiState
) {
    LoopifyTheme {
        HomeScreen(homeUiState = homeUiState,
            onNavigateDetailScreen = {})
    }
}

