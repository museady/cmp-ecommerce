package com.museady.cmp.ecommerce.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.museady.cmp.ecommerce.core.entity.Category

/**
 * Top-level navigation graph. Navigation is organized as explained at
 * https://d.android.com/jetpack/compose/nav-adaptive
 *
 * The navigation graph defined in this file defines the different top level routes. Navigation
 * within each route is handled using state and Back Handlers.
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
            isCompact
        ) { category ->
            val topLevelNavOptions = navigationState.topLevelNavOptions

            with(navController) {
                when (category) {
                    Category.HEADPHONES -> navigateToHeadphones(topLevelNavOptions)
                    Category.SPEAKRS -> navigateToSpeakers(topLevelNavOptions)
                    Category.EARPHONES -> navigateToEarphones(topLevelNavOptions)
                }
            }
        }

        headphoneScreen(isCompact)

        speakersScreen(isCompact)

        earphonesScreen(isCompact)
    }
}
