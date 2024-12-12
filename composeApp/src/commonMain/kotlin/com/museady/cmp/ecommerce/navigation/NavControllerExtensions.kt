package com.museady.cmp.ecommerce.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions

/**
 * Provides navigation extensions for the `NavController` to streamline navigation between
 * the app's main screens: Home, Category, and Product Details.
 */

fun NavController.navigateHome(
    navOptions: NavOptions
) = navigate(route = HomeRoute, navOptions = navOptions)

fun NavController.navigateToCategory(
    categoryOrdinal: Int,
    navOptions: NavOptions
) = navigate(route = CategoryRoute(categoryOrdinal = categoryOrdinal), navOptions = navOptions)

fun NavController.navigateToProductDetails(productId: Int) =
    navigate(route = ProductDetailsRoute(productId))
