package com.onurkaraduman.loopify.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.onurkaraduman.loopify.ui.loopify_navigator.LoopifyNavigator
import com.onurkaraduman.loopify.ui.loopify_navigator.components.AppBar

@Composable
fun LoopifyNavGraph(startDestination: String) {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            val currentBackStackEntry = navController.currentBackStackEntryAsState().value
            val isHomeScreen = currentBackStackEntry?.destination?.route == Route.HomeScreen.route
            val isSearchScreen =
                currentBackStackEntry?.destination?.route == Route.SearchScreen.route
            val isCategoriesScreen =
                currentBackStackEntry?.destination?.route == Route.CategoriesScreen.route

            AppBar(showBackButton = !(isHomeScreen || isSearchScreen || isCategoriesScreen))
        },
        content = { padding ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                NavHost(navController = navController, startDestination = startDestination) {
                    composable(route = Route.HomeScreen.route) {

                        LoopifyNavigator()
                    }

                }
            }
        }
    )
}
