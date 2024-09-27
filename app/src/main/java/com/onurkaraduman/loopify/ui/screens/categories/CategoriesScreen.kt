package com.onurkaraduman.loopify.ui.screens.categories

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.onurkaraduman.loopify.ui.screens.categories.components.CategoriesList
import com.onurkaraduman.loopify.ui.screens.common.ErrorScreen
import com.onurkaraduman.loopify.ui.screens.common.LoadingScreen

@Composable
fun CategoriesScreen(
    uiState: CategoriesUiState,
    onNavigateToProductScreen: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        when {
            uiState.isLoading -> {
                LoadingScreen()
            }

            uiState.categoriesList.isNotEmpty() -> {
                CategoriesList(
                    categories = uiState.categoriesList,
                    onClick = { endPoint -> onNavigateToProductScreen(endPoint) })
            }

            uiState.errorMessage != null -> {
                ErrorScreen(message = uiState.errorMessage)
            }
        }
    }
}