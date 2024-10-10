package com.onurkaraduman.loopify.ui.screens.categories

import AppToolbar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import com.onurkaraduman.loopify.ui.screens.categories.components.CategoriesList
import com.onurkaraduman.loopify.ui.screens.common.ErrorScreen
import com.onurkaraduman.loopify.ui.screens.common.LoadingScreen
import com.onurkaraduman.loopify.ui.screens.main.MainViewModel
import com.onurkaraduman.loopify.ui.screens.main.ToolbarState
import com.onurkaraduman.loopify.ui.theme.LoopifyTheme

@Composable
fun CategoriesScreen(
    uiState: CategoriesUiState,
    onNavigateToProductScreen: (String) -> Unit,
    mainViewModel: MainViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit) {
        mainViewModel.updateToolbarState(ToolbarState(title = "Categories"))
    }

    Scaffold(
        topBar = {
            val toolbarState by mainViewModel.toolbarState.collectAsState()
            AppToolbar(
                toolbarState = toolbarState,
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
                uiState.isLoading -> {
                    LoadingScreen()
                }

                uiState.categoriesList.isNotEmpty() -> {
                    CategoriesList(
                        categories = uiState.categoriesList,
                        onClick = { endPoint -> onNavigateToProductScreen(endPoint) })
                }

                uiState.errorMessage != null -> {
                    ErrorScreen(message = uiState.errorMessage, onClick = {})
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCategoriesScreen(
    @PreviewParameter(CategoryPreviewProvider::class) categoriesUiState: CategoriesUiState
) {
    LoopifyTheme {
        CategoriesScreen(
            uiState = categoriesUiState,
            onNavigateToProductScreen = {},
        )
    }

}