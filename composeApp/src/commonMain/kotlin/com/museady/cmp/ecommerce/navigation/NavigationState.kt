package com.museady.cmp.ecommerce.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.museady.cmp.ecommerce.core.entity.Category

/**
 * Creates and remembers a `NavigationState` instance, initializing it with a `NavHostController`.
 *
 * @param navController The navigation controller for managing navigation actions.
 * @return A `NavigationState` object that encapsulates navigation logic.
 */
@Composable
fun rememberNavigationState(
    navController: NavHostController = rememberNavController(),
): NavigationState {
    return remember(
        navController,
    ) {
        NavigationState(
            navController = navController,
        )
    }
}

/**
 * Manages navigation state and provides helper functions for navigating between top-level app destinations.
 *
 * @property navController The `NavHostController` used to manage navigation within the app.
 */
@Stable
class NavigationState(
    val navController: NavHostController,
) {
    private val topLevelNavOptions by lazy {
        navOptions {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
        }
    }

    fun navigateToHome() {
        navController.navigateHome(navOptions = topLevelNavOptions)
    }

    fun navigateToProductDetails(productId: Int) {
        navController.navigateToProductDetails(productId)
    }

    fun navigateToCategory(category: Category) {
        navController.navigateToCategory(
            category.ordinal,
            topLevelNavOptions
        )
    }
}
