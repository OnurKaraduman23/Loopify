package com.onurkaraduman.loopify.ui.navigation

sealed class Route(
    val route: String
) {
    data object HomeScreen: Route(route = "homeScreen")
    data object CategoriesScreen: Route(route = "categoriesScreen")
    data object SearchScreen: Route(route = "searchScreen")
    data object DetailScreen: Route(route = "detailScreen/{id}")
}