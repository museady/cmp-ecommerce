package com.museady.cmp.ecommerce.navigation

import kotlinx.serialization.Serializable

/**
 * This file contains route definitions and navigation helper functions for the app's main screens.
 * It defines `@Serializable` route objects for Home, Category, and ProductDetails screens,
 * along with `NavController` extensions to simplify navigation between these destinations.
 *
 * The purpose of this file is to centralize navigation logic and maintain clean separation
 * between routes and UI code.
 */

/**
 * Represents the route for the Home screen.
 *
 * This is a simple route object without any parameters.
 */
@Serializable
data object HomeRoute

/**
 * Represents the route for the Category screen.
 *
 * This route includes the ordinal value of the category to navigate to.
 *
 * @param categoryOrdinal The ordinal value representing the category in the list of categories.
 */
@Serializable
data class CategoryRoute(val categoryOrdinal: Int)

/**
 * Represents the route for the Product Details screen.
 *
 * This route includes the ID of the product to navigate to.
 *
 * @param productId The ID of the product to display in the product details screen.
 */
@Serializable
data class ProductDetailsRoute(val productId: Int)