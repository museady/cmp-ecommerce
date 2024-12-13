package com.museady.cmp.ecommerce.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions

/**
 * This file contains navigation extensions for the `NavController` to streamline navigation
 * between the app's main screens: Home, Category, and Product Details.
 */

fun NavController.navigateHome(navOptions: NavOptions) {
    navigate(route = HomeRoute, navOptions = navOptions)
}

fun NavController.navigateToHeadphones(navOptions: NavOptions) {
    navigate(route = HeadphonesRoute, navOptions = navOptions)
}

fun NavController.navigateToSpeakers(navOptions: NavOptions) {
    navigate(route = SpeakersRoute, navOptions = navOptions)
}

fun NavController.navigateToEarphones(navOptions: NavOptions) {
    navigate(route = EarphonesRoute, navOptions = navOptions)
}

fun NavController.navigateToProductDetails(productId: Int) =
    navigate(route = ProductDetailsRoute(productId))
