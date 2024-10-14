package com.onurkaraduman.loopify.ui.screens.categories

import AppToolbar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.onurkaraduman.loopify.ui.screens.categories.components.CategoriesList
import com.onurkaraduman.loopify.ui.screens.common.ErrorScreen
import com.onurkaraduman.loopify.ui.screens.common.LoadingScreen
import com.onurkaraduman.loopify.ui.screens.main.MainContract.MainUiAction
import com.onurkaraduman.loopify.ui.screens.main.MainContract.MainUiEffect
import com.onurkaraduman.loopify.ui.screens.main.MainContract.ToolbarState
import com.onurkaraduman.loopify.ui.theme.LoopifyTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Composable
fun CategoriesScreen(
    uiState: CategoriesUiState,
    onNavigateToProductScreen: (String) -> Unit,
    onToolbarAction: (MainUiAction) -> Unit,
    toolbarUiEffect: Flow<MainUiEffect>,
    onNavigateCart: () -> Unit
) {
    val toolbarState = remember { mutableStateOf(ToolbarState()) }

    LaunchedEffect(toolbarUiEffect) {
        toolbarUiEffect.collect { effect ->
            when (effect) {
                is MainUiEffect.SetTitle -> {
                    toolbarState.value = toolbarState.value.copy(title = "Category")
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
            onNavigateCart = {},
            onToolbarAction = {},
            toolbarUiEffect = flow { }
        )
    }

}