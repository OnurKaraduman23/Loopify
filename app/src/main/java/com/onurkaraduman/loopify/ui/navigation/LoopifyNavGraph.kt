package com.onurkaraduman.loopify.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.onurkaraduman.loopify.ui.loopify_navigator.LoopifyNavigator
import com.onurkaraduman.loopify.ui.screens.main.MainViewModel

@Composable
fun LoopifyNavGraph(startDestination: String) {
    val navController = rememberNavController()



    Surface(
        modifier = Modifier
            .fillMaxSize()

    ) {
        NavHost(navController = navController, startDestination = startDestination) {
            composable(route = Route.HomeScreen.route) {
                LoopifyNavigator()
            }

        }
    }
}


