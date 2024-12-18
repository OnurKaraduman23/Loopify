package com.onurkaraduman.loopify.ui.screens.search

import AppToolbar
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.onurkaraduman.loopify.ui.components.ProductList
import com.onurkaraduman.loopify.ui.screens.common.ErrorScreen
import com.onurkaraduman.loopify.ui.screens.common.LoadingScreen
import com.onurkaraduman.loopify.ui.screens.main.MainContract.MainUiAction
import com.onurkaraduman.loopify.ui.screens.main.MainContract.MainUiEffect
import com.onurkaraduman.loopify.ui.screens.main.MainContract.ToolbarState
import com.onurkaraduman.loopify.ui.screens.search.SearchContract.SearchUiAction
import com.onurkaraduman.loopify.ui.screens.search.SearchContract.SearchUiState
import com.onurkaraduman.loopify.ui.screens.search.components.EmptySearchScreen
import com.onurkaraduman.loopify.ui.screens.search.components.SearchBar
import com.onurkaraduman.loopify.ui.theme.LoopifyTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Composable
fun SearchScreen(
    searchUiState: SearchUiState,
    onAction: (SearchUiAction) -> Unit,
    onNavigateDetailScreen: (Int) -> Unit,
    onToolbarAction: (MainUiAction) -> Unit,
    toolbarUiEffect: Flow<MainUiEffect>,
    onNavigateCart: () -> Unit
) {

    val toolbarState = remember { mutableStateOf(ToolbarState()) }

    LaunchedEffect(toolbarUiEffect) {
        toolbarUiEffect.collect { effect ->
            when (effect) {
                is MainUiEffect.SetTitle -> {
                    toolbarState.value = toolbarState.value.copy(title = "Search")
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
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SearchContent(searchQuery = searchUiState.searchQuery.orEmpty(), onQueryChange = {
                onAction(
                    SearchUiAction.ChangeQuery(it)
                )
            },
                onSearchClick = {
                    onAction(SearchUiAction.SearchProducts)
                }
            )
            when {
                searchUiState.isLoading -> {
                    LoadingScreen()
                }

                searchUiState.productList.isNotEmpty() -> {
                    ProductList(
                        searchUiState.productList,
                        onClick = { id ->
                            onNavigateDetailScreen(id)
                        })
                }


                searchUiState.errorMessage != null -> {
                    ErrorScreen(
                        searchUiState.errorMessage,
                        onClick = { onAction(SearchUiAction.RetryErrorScreenClick) })
                }

                searchUiState.searchQuery.isNullOrBlank() -> {
                    EmptySearchScreen()
                }

            }
        }
    }
}

@Composable
fun SearchContent(
    searchQuery: String,
    onQueryChange: (String) -> Unit,
    onSearchClick: () -> Unit,

    ) {
    Spacer(modifier = Modifier.height(8.dp))
    SearchBar(text = searchQuery, onSearch = { onSearchClick() }, onValueChange = onQueryChange)
}


@Preview(showBackground = true)
@Composable
fun PreviewSearchScreen(
    @PreviewParameter(SearchScreenPreviewProvider::class) searchUiState: SearchUiState
) {
    LoopifyTheme {
        SearchScreen(
            searchUiState = searchUiState,
            onNavigateDetailScreen = {},
            onAction = { },
            onNavigateCart = {},
            onToolbarAction = {},
            toolbarUiEffect = flow { }

        )
    }
}
