package com.museady.cmp.ecommerce.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.museady.cmp.ecommerce.core.entity.Category
import com.museady.cmp.ecommerce.ui.CategoryScreen
import com.museady.cmp.ecommerce.ui.HomeScreen
import com.museady.cmp.ecommerce.ui.ProductDetailsScreen

/**
 * Sets up the navigation graph for the app's primary screens: Home, Category, and Product Details.
 *
 * This file centralizes the navigation logic, ensuring seamless transitions between screens and
 * passing the necessary data to each destination. It defines composable functions for adding
 * these screens to the navigation graph.
 *
 */

fun NavGraphBuilder.homeScreen(
    isCompat: Boolean,
    onCategoryClick: (category: Category) -> Unit,
    onProductClick: (id: Int) -> Unit
) {
    composable<HomeRoute> {
        HomeScreen(isCompat, onCategoryClick, onProductClick)
    }
}

fun NavGraphBuilder.categoryScreen(
    isCompat: Boolean,
    onCategoryClick: (category: Category) -> Unit,
    onProductClick: (id: Int) -> Unit,
) {
    composable<CategoryRoute> { backStackEntry ->
        val category = Category.entries.first {
            it.ordinal == backStackEntry.arguments?.getInt("categoryOrdinal")
        }

        CategoryScreen(
            isCompact = isCompat,
            category = category,
            onCategoryClick = onCategoryClick,
            onProductCLick = onProductClick
        )
    }
}

fun NavGraphBuilder.productDetailsScreen(
    isCompat: Boolean,
    onOtherProductClick: (productId: Int) -> Unit,
    onCategoryClick: (category: Category) -> Unit
) {
    composable<ProductDetailsRoute> { backStackEntry ->
        val productId = backStackEntry.arguments?.getInt("productId") ?: 0

        ProductDetailsScreen(
            productId = productId,
            isCompat = isCompat,
            onAddToCartClick = { _, _ -> },
            onOtherProductClick = onOtherProductClick,
            onCategoryClick = onCategoryClick
        )
    }
}
