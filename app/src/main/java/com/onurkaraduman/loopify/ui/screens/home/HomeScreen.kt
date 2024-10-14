package com.onurkaraduman.loopify.ui.screens.home

import AppToolbar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.onurkaraduman.loopify.ui.components.ProductList
import com.onurkaraduman.loopify.ui.screens.common.ErrorScreen
import com.onurkaraduman.loopify.ui.screens.common.LoadingScreen
import com.onurkaraduman.loopify.ui.screens.home.HomeContract.HomeUiAction
import com.onurkaraduman.loopify.ui.screens.home.HomeContract.HomeUiState
import com.onurkaraduman.loopify.ui.screens.main.MainContract.MainUiAction
import com.onurkaraduman.loopify.ui.screens.main.MainContract.MainUiEffect
import com.onurkaraduman.loopify.ui.screens.main.MainContract.ToolbarState
import com.onurkaraduman.loopify.ui.theme.LoopifyTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


@Composable
fun HomeScreen(
    homeUiState: HomeUiState,
    onAction: (HomeUiAction) -> Unit,
    onNavigateDetailScreen: (Int) -> Unit,
    onToolbarAction: (MainUiAction) -> Unit,
    toolbarUiEffect: Flow<MainUiEffect>,
    onNavigateCartScreen: () -> Unit
) {
    val toolbarState = remember { mutableStateOf(ToolbarState()) }

    LaunchedEffect(toolbarUiEffect) {
        toolbarUiEffect.collect { effect ->
            when (effect) {
                is MainUiEffect.SetTitle -> {
                    toolbarState.value = toolbarState.value.copy(title = effect.userName)
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
                onCartClick = onNavigateCartScreen
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier.fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                modifier = Modifier.align(Alignment.Start).padding(start = 8.dp),
                text = "Recommended For You",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            when {
                homeUiState.isLoading -> LoadingScreen()
                homeUiState.productList.isNotEmpty() -> {
                    ProductList(
                        homeUiState.productList,
                        onClick = { onNavigateDetailScreen(it) })
                }

                else -> ErrorScreen(
                    homeUiState.errorMessage.toString(),
                    onClick = { onAction(HomeUiAction.RetryErrorScreenClick) })
            }
        }

    }

}


@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen(
    @PreviewParameter(HomeScreenPreviewProvider::class) homeUiState: HomeUiState
) {
    LoopifyTheme {
        HomeScreen(
            homeUiState = homeUiState,
            onNavigateDetailScreen = {},
            onAction = {},
            onNavigateCartScreen = {},
            onToolbarAction = {},
            toolbarUiEffect = flow {}
        )
    }
}

