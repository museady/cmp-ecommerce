package com.museady.cmp.ecommerce.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost

/**
 * Configures the `NavHost` with the app's navigation graph,
 * defining the routes and their associated composables for the Home, Category,
 * and Product Details screens. It uses the provided `NavigationState` to manage
 * navigation actions and maintains navigation behavior across the app.
 *
 * @param navigationState The state object that holds the `NavController` and handles navigation actions.
 * @param isCompact Determines the UI layout mode (compact or expanded).
 * @param modifier The `Modifier` applied to the `NavHost` for customization.
 */
@Composable
fun AppNavHost(
    navigationState: NavigationState,
    isCompact: Boolean,
    modifier: Modifier = Modifier,
) {
    val navController = navigationState.navController

    NavHost(
        navController = navController,
        startDestination = HomeRoute,
        modifier = modifier,
    ) {
        homeScreen(
            isCompat = isCompact,
            onCategoryClick = navigationState::navigateToCategory,
            onProductClick = navigationState::navigateToProductDetails
        )

        categoryScreen(
            isCompat = isCompact,
            onCategoryClick = navigationState::navigateToCategory,
            onProductClick = navigationState::navigateToProductDetails
        )

        productDetailsScreen(
            isCompat = isCompact,
            onCategoryClick = navigationState::navigateToCategory,
            onOtherProductClick = navigationState::navigateToProductDetails
        )
    }
}
