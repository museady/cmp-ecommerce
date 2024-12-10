package com.museady.cmp.ecommerce.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.util.trace
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.museady.cmp.ecommerce.navigation.TopLevelDestination.EARPHONES
import com.museady.cmp.ecommerce.navigation.TopLevelDestination.HEADPHONES
import com.museady.cmp.ecommerce.navigation.TopLevelDestination.HOME
import com.museady.cmp.ecommerce.navigation.TopLevelDestination.SPEAKERS

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

@Stable
class NavigationState(
    val navController: NavHostController,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    /**
     * Map of top level destinations to be used in the TopBar, BottomBar and NavRail. The key is the
     * route.
     */
    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() {
            return TopLevelDestination.entries.firstOrNull { topLevelDestination ->
                currentDestination?.hasRoute(route = topLevelDestination.route) == true
            }
        }

    val topLevelNavOptions by lazy {
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
            // Restore state when reselecting a previously selected item
            restoreState = true
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
                HOME -> navController.navigateHome(topLevelNavOptions)
                HEADPHONES -> navController.navigateToHeadphones(topLevelNavOptions)
                SPEAKERS -> navController.navigateToSpeakers(topLevelNavOptions)
                EARPHONES -> navController.navigateToEarphones(topLevelNavOptions)
            }
        }
    }
}
