package com.onurkaraduman.loopify.ui.screens.cart

import AppToolbar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.onurkaraduman.loopify.ui.screens.main.MainContract
import com.onurkaraduman.loopify.ui.screens.main.MainContract.MainUiEffect
import com.onurkaraduman.loopify.ui.screens.main.MainContract.MainUiAction
import com.onurkaraduman.loopify.ui.theme.LoopifyTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Composable
fun CartScreen(
    onToolbarAction: (MainUiAction) -> Unit,
    toolbarUiEffect: Flow<MainUiEffect>,
    onBackClickToolbar: () -> Unit,
) {

    val toolbarState = remember { mutableStateOf(MainContract.ToolbarState()) }

    LaunchedEffect(toolbarUiEffect) {
        toolbarUiEffect.collect { effect ->
            when (effect) {
                is MainUiEffect.SetTitle -> {
                    toolbarState.value = toolbarState.value.copy(title = "Cart", showBackButton = true)
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        onToolbarAction(MainContract.MainUiAction.FetchToolbar)
    }

    Scaffold(
        topBar = {
            AppToolbar(
                toolbarState = toolbarState.value,
                onBackClick = onBackClickToolbar,
            )
        }
    ) { paddingValues ->

        Surface(
            elevation = 4.dp,
            modifier = Modifier.fillMaxWidth()
                .padding(
                    top = paddingValues.calculateTopPadding()
                )
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Total", style = MaterialTheme.typography.h5)
                Text(text = "200 $", style = MaterialTheme.typography.h5)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCartScreen() {
    LoopifyTheme {
        CartScreen(onBackClickToolbar = {},
            onToolbarAction = {},
            toolbarUiEffect = flow {  })
    }
}