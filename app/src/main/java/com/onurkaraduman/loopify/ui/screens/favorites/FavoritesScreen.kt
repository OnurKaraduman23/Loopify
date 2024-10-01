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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.onurkaraduman.loopify.ui.screens.favorites.FavoritesContract.FavoritesUiAction
import com.onurkaraduman.loopify.ui.screens.favorites.FavoritesContract.FavoritesUiState
import com.onurkaraduman.loopify.ui.screens.favorites.component.EmptyFavoritesScreen
import com.onurkaraduman.loopify.ui.screens.favorites.component.FavoriteCard
import com.onurkaraduman.loopify.ui.screens.main.MainViewModel
import com.onurkaraduman.loopify.ui.screens.main.ToolbarState

@Composable
fun FavoriteScreen(
    favoritesUiState: FavoritesUiState,
    onAction: (FavoritesUiAction) -> Unit,
    onNavigateDetailScreen: (Int) -> Unit,
    mainViewModel: MainViewModel,
    onBackClickToolbar: () -> Unit
) {

    LaunchedEffect(Unit) {
        onAction(FavoritesUiAction.RefreshFavorites)
        mainViewModel.updateToolbarState(ToolbarState(title = "Favorites", showBackButton = true))
    }

    Scaffold(
        topBar = {
            val toolbarState by mainViewModel.toolbarState.collectAsState()
            AppToolbar(
                toolbarState = toolbarState,
                onBackClick = onBackClickToolbar
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