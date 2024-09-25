package com.onurkaraduman.loopify.ui.screens.home

import ProductCard
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.onurkaraduman.loopify.domain.model.products.ProductsModel
import com.onurkaraduman.loopify.ui.screens.common.EmptyScreen
import com.onurkaraduman.loopify.ui.screens.common.LoadingScreen
import com.onurkaraduman.loopify.ui.theme.LoopifyTheme


@Composable
fun HomeScreen(
    homeUiState: HomeUiState,
    onNavigateDetailScreen: (Int) -> Unit
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        Text(modifier = Modifier.align(Alignment.Start).padding(start = 8.dp), text = "Recommended For You", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(8.dp))
        when {
            homeUiState.isLoading -> LoadingScreen()
            homeUiState.productList.isNotEmpty() -> {
                ProductList(
                    homeUiState.productList,
                    onClick = { onNavigateDetailScreen(it) })
            }

            else -> EmptyScreen(homeUiState.errorMessage.toString())
        }
    }

}

@Composable
fun ProductList(
    products: List<ProductsModel>,
    onClick: (Int) -> Unit
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
                product = product,
                onClick = { onClick(it) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen(
    @PreviewParameter(HomeScreenPreviewProvider::class) homeUiState: HomeUiState
) {
    LoopifyTheme {
        HomeScreen(homeUiState = homeUiState,
            onNavigateDetailScreen = {},
            )
    }
}

