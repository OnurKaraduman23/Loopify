package com.onurkaraduman.loopify.ui.screens.favorites

import AppToolbar
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.onurkaraduman.loopify.ui.screens.favorites.FavoritesContract.FavoritesUiAction
import com.onurkaraduman.loopify.ui.screens.favorites.FavoritesContract.FavoritesUiState
import com.onurkaraduman.loopify.ui.screens.favorites.component.EmptyFavoritesScreen
import com.onurkaraduman.loopify.ui.screens.favorites.component.FavoriteCard
import com.onurkaraduman.loopify.ui.screens.main.MainContract.MainUiAction
import com.onurkaraduman.loopify.ui.screens.main.MainContract.MainUiEffect
import com.onurkaraduman.loopify.ui.screens.main.MainContract.ToolbarState
import com.onurkaraduman.loopify.ui.theme.LoopifyTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


@Composable
fun FavoriteScreen(
    favoritesUiState: FavoritesUiState,
    onAction: (FavoritesUiAction) -> Unit,
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
                    toolbarState.value = toolbarState.value.copy(title = "Favorites")
                }

            }
        }

    }

    LaunchedEffect(Unit) {
        onToolbarAction(MainUiAction.FetchToolbar)
        onAction(FavoritesUiAction.RefreshFavorites)
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
                .padding(top = paddingValues.calculateTopPadding()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            when {
                favoritesUiState.productFavorites.isNotEmpty() && favoritesUiState.productFavorites.all { it != null } -> {
                    Log.e("Dante", favoritesUiState.productFavorites.toString())
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        items(favoritesUiState.productFavorites) { favorite ->
                            FavoriteCard(
                                favoriteProduct = favorite,
                                onClick = { onNavigateDetailScreen(favorite.id) },
                                onClickDelete = {
                                    onAction(
                                        FavoritesUiAction.DeleteToFavoriteClick(
                                            favorite.id,
                                            favorite.title,
                                            favorite.price,
                                            favorite.category,
                                            favorite.image
                                        )
                                    )
                                })
                        }
                    }

                }

                else -> {
                    EmptyFavoritesScreen()
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewFavoritesScreen(
    @PreviewParameter(FavoritesScreenPreviewProvider::class) favoritesUiState: FavoritesUiState
) {
    LoopifyTheme {
        FavoriteScreen(
            favoritesUiState = favoritesUiState,
            onAction = {},
            onNavigateDetailScreen = {},
            onNavigateCart = {},
            onToolbarAction = {},
            onBackClickToolbar = {},
            toolbarUiEffect = flow { }

        )
    }
}