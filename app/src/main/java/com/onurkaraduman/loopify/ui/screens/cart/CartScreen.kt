package com.onurkaraduman.loopify.ui.screens.cart

import AppToolbar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.onurkaraduman.loopify.ui.screens.main.MainViewModel
import com.onurkaraduman.loopify.ui.screens.main.ToolbarState

@Composable
fun CartScreen(
    mainViewModel: MainViewModel = hiltViewModel(),
    onBackClickToolbar: () -> Unit,
) {

    LaunchedEffect(Unit) {
        mainViewModel.updateToolbarState(ToolbarState(title = "Cart", showBackButton = true))
    }

    Scaffold(
        topBar = {
            val toolbarState by mainViewModel.toolbarState.collectAsState()
            AppToolbar(
                toolbarState = toolbarState,
                onBackClick = onBackClickToolbar,
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Cart Screen")
        }
    }
}