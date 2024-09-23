package com.onurkaraduman.loopify.ui.navigation

import androidx.compose.runtime.Composable

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.onurkaraduman.loopify.ui.loopify_navigator.LoopifyNavigator

@Composable
fun LoopifyNavGraph(startDestination: String){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination= startDestination){
        composable(route = Route.HomeScreen.route) {
            LoopifyNavigator()
        }
    }
}