package com.museady.cmp.ecommerce.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.museady.cmp.ecommerce.core.entity.Category
import com.museady.cmp.ecommerce.ui.CategoryScreen
import com.museady.cmp.ecommerce.ui.HomeScreen
import com.museady.cmp.ecommerce.ui.ProductDetailsScreen

/**
 * This file Sets up the navigation graph for the app's primary screens: Home, Headphones, Speakers,
 * and Product Details.
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

fun NavGraphBuilder.headphonesScreen(
    isCompat: Boolean,
    onCategoryClick: (category: Category) -> Unit,
    onProductClick: (id: Int) -> Unit
) {
    composable<HeadphonesRoute> {
        CategoryScreen(
            isCompact = isCompat,
            category = Category.HEADPHONES,
            onCategoryClick = onCategoryClick,
            onProductCLick = onProductClick
        )
    }
}

fun NavGraphBuilder.speakersScreen(
    isCompat: Boolean,
    onCategoryClick: (category: Category) -> Unit,
    onProductClick: (id: Int) -> Unit
) {
    composable<SpeakersRoute> {
        CategoryScreen(
            isCompact = isCompat,
            category = Category.SPEAKERS,
            onCategoryClick = onCategoryClick,
            onProductCLick = onProductClick
        )
    }
}

fun NavGraphBuilder.earphonesScreen(
    isCompat: Boolean,
    onCategoryClick: (category: Category) -> Unit,
    onProductClick: (id: Int) -> Unit
) {
    composable<EarphonesRoute> {
        CategoryScreen(
            isCompact = isCompat,
            category = Category.EARPHONES,
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
