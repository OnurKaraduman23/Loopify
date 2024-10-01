package com.onurkaraduman.loopify.ui.navigation

sealed class Route(
    val route: String
) {
    data object HomeScreen : Route(route = "homeScreen")
    data object CategoriesScreen : Route(route = "categoriesScreen")
    data object SearchScreen : Route(route = "searchScreen")
    data object FavoritesScreen : Route(route = "favoritesScreen")
    data object DetailScreen : Route(route = "detailScreen/{id}")
    data object CategoryProductsScreen : Route(route = "categoryProductsScreen/{endPoint}")
}