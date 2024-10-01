package com.onurkaraduman.loopify.ui.loopify_navigator

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.onurkaraduman.loopify.R
import com.onurkaraduman.loopify.ui.loopify_navigator.components.BottomNavigationItem
import com.onurkaraduman.loopify.ui.loopify_navigator.components.LoopifyBottomNavigation
import com.onurkaraduman.loopify.ui.navigation.Route
import com.onurkaraduman.loopify.ui.screens.categories.CategoriesScreen
import com.onurkaraduman.loopify.ui.screens.categories.CategoriesViewModel
import com.onurkaraduman.loopify.ui.screens.category_products.CategoryProductsScreen
import com.onurkaraduman.loopify.ui.screens.category_products.CategoryProductsViewModel
import com.onurkaraduman.loopify.ui.screens.detail.DetailScreen
import com.onurkaraduman.loopify.ui.screens.detail.DetailViewModel
import com.onurkaraduman.loopify.ui.screens.favorites.FavoriteScreen
import com.onurkaraduman.loopify.ui.screens.favorites.FavoritesViewModel
import com.onurkaraduman.loopify.ui.screens.home.HomeScreen
import com.onurkaraduman.loopify.ui.screens.home.HomeViewModel
import com.onurkaraduman.loopify.ui.screens.main.MainViewModel
import com.onurkaraduman.loopify.ui.screens.search.SearchScreen
import com.onurkaraduman.loopify.ui.screens.search.SearchViewModel


@Composable
fun LoopifyNavigator() {

    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.ic_home, text = "Home"),
            BottomNavigationItem(icon = R.drawable.ic_search, text = "Search"),
            BottomNavigationItem(icon = R.drawable.ic_category, text = "Category"),
            BottomNavigationItem(icon = R.drawable.ic_favorite_fill, text = "Favorites"),
        )
    }

    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }
    selectedItem = when (backStackState?.destination?.route) {
        Route.HomeScreen.route -> 0
        Route.SearchScreen.route -> 1
        Route.CategoriesScreen.route -> 2
        Route.FavoritesScreen.route -> 3
        else -> 0
    }


    //Hide the bottom navigation when the user is in the other screens
    val isBottomBarVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == Route.HomeScreen.route ||
                backStackState?.destination?.route == Route.SearchScreen.route ||
                backStackState?.destination?.route == Route.CategoriesScreen.route ||
                backStackState?.destination?.route == Route.FavoritesScreen.route
    }


    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
        if (isBottomBarVisible) {
            LoopifyBottomNavigation(
                items = bottomNavigationItems,
                selectedItem = selectedItem,
                onItemClick = { index ->
                    when (index) {
                        0 -> navigateToTab(
                            navController = navController,
                            route = Route.HomeScreen.route
                        )

                        1 -> navigateToTab(
                            navController = navController,
                            route = Route.SearchScreen.route
                        )

                        2 -> navigateToTab(
                            navController = navController,
                            route = Route.CategoriesScreen.route
                        )

                        3 -> navigateToTab(
                            navController = navController,
                            route = Route.FavoritesScreen.route
                        )
                    }
                }
            )
        }

    }) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(route = Route.HomeScreen.route) { backStackEntry ->
                val homeViewModel: HomeViewModel = hiltViewModel()
                val mainViewModel: MainViewModel = hiltViewModel() //for toolbar
                val homeUiState by homeViewModel.homeUiState.collectAsStateWithLifecycle()
                HomeScreen(homeUiState = homeUiState, onNavigateDetailScreen = { productId ->
                    navigateToDetails(navController = navController, productId = productId)
                }, onAction = homeViewModel::onAction, mainViewModel = mainViewModel)
            }

            composable(route = Route.SearchScreen.route) {
                val viewModel: SearchViewModel = hiltViewModel()
                val mainViewModel: MainViewModel = hiltViewModel() //for toolbar
                val uiState by viewModel.searchUiState.collectAsStateWithLifecycle()
                SearchScreen(
                    searchUiState = uiState,
                    onAction = viewModel::onAction,
                    onNavigateDetailScreen = { productId ->
                        navigateToDetails(navController = navController, productId = productId)
                    }, mainViewModel = mainViewModel
                )
            }

            composable(route = Route.CategoriesScreen.route)
            {
                val viewmodel: CategoriesViewModel = hiltViewModel()
                val mainViewModel: MainViewModel = hiltViewModel()
                val uiState by viewmodel.categoriesUiState.collectAsStateWithLifecycle()
                CategoriesScreen(uiState = uiState, onNavigateToProductScreen = { endPoint ->
                    navigateToCategoryProducts(navController = navController, endPoint = endPoint)
                }, mainViewModel = mainViewModel)
            }

            composable(route = Route.FavoritesScreen.route) {
                val viewModel: FavoritesViewModel = hiltViewModel()
                val mainViewModel: MainViewModel = hiltViewModel()
                val uiState by viewModel.favoritesUiState.collectAsState()
                FavoriteScreen(
                    favoritesUiState = uiState,
                    onAction = viewModel::onAction,
                    onNavigateDetailScreen = { productId ->
                        navigateToDetails(navController = navController, productId = productId)
                    }, mainViewModel = mainViewModel,
                    onBackClickToolbar = { navController.popBackStack() })
            }

            composable(
                route = Route.DetailScreen.route,
                arguments = listOf(navArgument("id") { type = NavType.IntType })
            ) { backStackEntry ->
                val viewModel: DetailViewModel = hiltViewModel()
                val mainViewModel: MainViewModel = hiltViewModel() // for toolbar
                val uiState by viewModel.detailUiState.collectAsStateWithLifecycle()
                val uiEffect = viewModel.uiEffect
                DetailScreen(
                    detailUiState = uiState,
                    onAction = viewModel::onAction,
                    detailUiEffect = uiEffect,
                    onNavigateCardScreen = {},
                    mainViewModel = mainViewModel,
                    onBackClickToolbar = { navController.popBackStack() })
            }

            composable(
                route = Route.CategoryProductsScreen.route,
                arguments = listOf(navArgument("endPoint") { type = NavType.StringType })
            ) { backStackEntry ->
                val viewModel: CategoryProductsViewModel = hiltViewModel()
                val mainViewModel: MainViewModel = hiltViewModel()
                val uiState by viewModel.categoryProductsUiState.collectAsStateWithLifecycle()
                CategoryProductsScreen(
                    categoryProductsUiState = uiState,
                    onNavigateDetailScreen = { id ->
                        navigateToDetails(
                            navController = navController,
                            productId = id
                        )
                    }, mainViewModel = mainViewModel,
                    onBackClickToolbar = { navController.popBackStack() })
            }

        }
    }
}

private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { screen_route ->
            popUpTo(screen_route) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}

private fun navigateToDetails(navController: NavController, productId: Int) {
    navController.navigate(
        route = Route.DetailScreen.route.replace("{id}", productId.toString())
    )
}

private fun navigateToCategoryProducts(navController: NavController, endPoint: String) {
    navController.navigate(
        route = Route.CategoryProductsScreen.route.replace("{endPoint}", endPoint)
    )
}
