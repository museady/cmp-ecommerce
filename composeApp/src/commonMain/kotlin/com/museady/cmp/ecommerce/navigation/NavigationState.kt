package com.museady.cmp.ecommerce.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.util.trace
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
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
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    private val topLevelNavOptions by lazy {
        navOptions {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id)

            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
        }
    }

    /**
     * UI logic for navigating to a top level destination in the app. Top level destinations have
     * only one copy of the destination of the back stack, and save and restore state whenever you
     * navigate to and from it.
     *
     * @param topLevelDestination: The destination the app needs to navigate to.
     */
    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        trace("Navigation: ${topLevelDestination.name}") {
            when (topLevelDestination) {
                TopLevelDestination.HOME -> navController.navigateHome(
                    navOptions = topLevelNavOptions
                )

                TopLevelDestination.HEADPHONES -> navController.navigateToHeadphones(
                    navOptions = topLevelNavOptions
                )

                TopLevelDestination.SPEAKERS -> navController.navigateToSpeakers(
                    navOptions = topLevelNavOptions
                )

                TopLevelDestination.EARPHONES -> navController.navigateToEarphones(
                    navOptions = topLevelNavOptions
                )
            }
        }
    }

    /**
     * Navigates to the specified category screen based on the provided category.
     *
     * This function is used for navigating to category screens from the category list across the app.
     * Depending on the selected category, it triggers the appropriate navigation command
     * to navigate to the corresponding screen.
     * The navigation options are passed via [topLevelNavOptions].
     *
     * @param category The category to navigate to. This could be one of:
     *                 [Category.HEADPHONES], [Category.SPEAKERS], or [Category.EARPHONES].
     */
    fun navigateToCategory(category: Category) {
        when (category) {
            Category.HEADPHONES -> navController.navigateToHeadphones(
                navOptions = topLevelNavOptions
            )

            Category.SPEAKERS -> navController.navigateToSpeakers(
                navOptions = topLevelNavOptions
            )

            Category.EARPHONES -> navController.navigateToEarphones(
                navOptions = topLevelNavOptions
            )
        }
    }

    fun navigateToProductDetails(productId: Int) {
        navController.navigateToProductDetails(productId)
    }
}
