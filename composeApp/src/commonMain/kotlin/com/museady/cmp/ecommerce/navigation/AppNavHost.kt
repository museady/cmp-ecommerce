package com.museady.cmp.ecommerce.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.navOptions
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
        val topLevelNavOptions by lazy {
            navigationState.topLevelNavOptions
        }
        val productNavOptions = navOptions {}

        fun navigateToSpecificCategory(category: Category) {
            with(navController) {

                when (category) {
                    Category.HEADPHONES -> navigateToHeadphones(topLevelNavOptions)
                    Category.SPEAKRS -> navigateToSpeakers(topLevelNavOptions)
                    Category.EARPHONES -> navigateToEarphones(topLevelNavOptions)
                }
            }
        }

        homeScreen(
            isCompact, { category ->
            navigateToSpecificCategory(category)
        }){productId ->
            navController.navigateToProductDetails(productId, navOptions = { productNavOptions })
        }

        headphoneScreen(isCompact, { category ->
            navigateToSpecificCategory(category)
        }) { productId ->
            navController.navigateToProductDetails(productId, navOptions = { productNavOptions })

        }

        speakersScreen(isCompact, { category ->
            navigateToSpecificCategory(category)
        }) { productId ->
            navController.navigateToProductDetails(productId, navOptions = { productNavOptions })

        }
        earphonesScreen(isCompact, { category ->
            navigateToSpecificCategory(category)
        }) { productId ->
            navController.navigateToProductDetails(productId, navOptions = { productNavOptions })

        }

        productDetailsScreen(isCompact, onOtherProductClick = {
            navController.navigateToProductDetails(it) {
                navOptions {
//                    popUpTo(navController.graph.findla().id) {
//                        saveState = true
//                    }
//                    launchSingleTop = true
//                    restoreState = true
                }
            }
        }, onCategoryClick = {
            navigateToSpecificCategory(it)
        })
    }
}
