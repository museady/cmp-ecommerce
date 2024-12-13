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

@Serializable
data object HomeRoute

@Serializable
object HeadphonesRoute

@Serializable
object SpeakersRoute

@Serializable
object EarphonesRoute

@Serializable
data class ProductDetailsRoute(val productId: Int)