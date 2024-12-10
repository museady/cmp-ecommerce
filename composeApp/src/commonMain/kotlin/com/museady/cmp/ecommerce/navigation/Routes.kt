package com.museady.cmp.ecommerce.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import com.museady.cmp.ecommerce.core.entity.Category
import com.museady.cmp.ecommerce.ui.CategoryScreen
import com.museady.cmp.ecommerce.ui.HomeScreen
import com.museady.cmp.ecommerce.ui.ProductDetailsScreen
import kotlinx.serialization.Serializable

@Serializable
data object HomeRoute

fun NavController.navigateHome(navOptions: NavOptions) = navigate(route = HomeRoute, navOptions)

fun NavGraphBuilder.homeScreen(
    isCompat: Boolean,
    navigateToCategory: (category: Category) -> Unit,
    navigateToProduct: (id: Int) -> Unit
) {
    composable<HomeRoute> {
        HomeScreen(isCompat, navigateToCategory, navigateToProduct)
    }
}

@Serializable
object HeadphonesRoute

fun NavController.navigateToHeadphones(navOptions: NavOptions) =
    navigate(route = HeadphonesRoute, navOptions)

fun NavGraphBuilder.headphoneScreen(
    isCompat: Boolean,
    navigateToCategory: (category: Category) -> Unit,
    navigateToProduct: (id: Int) -> Unit,
) {

    composable<HeadphonesRoute> {
        CategoryScreen(
            isCompact = isCompat,
            category = Category.HEADPHONES,
            onCategoryClick = navigateToCategory,
            onProductCLick = navigateToProduct
        )
    }
}

@Serializable
object SpeakersRoute

fun NavController.navigateToSpeakers(navOptions: NavOptions) =
    navigate(route = SpeakersRoute, navOptions)

fun NavGraphBuilder.speakersScreen(
    isCompat: Boolean,
    navigateToCategory: (category: Category) -> Unit,
    navigateToProduct: (id: Int) -> Unit,
) {
    composable<SpeakersRoute> {
        CategoryScreen(
            isCompact = isCompat,
            category = Category.SPEAKERS,
            onCategoryClick = navigateToCategory,
            onProductCLick = navigateToProduct
        )
    }
}

@Serializable
object EarphonesRoute

fun NavController.navigateToEarphones(navOptions: NavOptions) =
    navigate(route = EarphonesRoute, navOptions)

fun NavGraphBuilder.earphonesScreen(
    isCompat: Boolean,
    navigateToCategory: (category: Category) -> Unit,
    navigateToProduct: (id: Int) -> Unit,
    ) {
    composable<EarphonesRoute> {
        CategoryScreen(
            isCompact = isCompat,
            category = Category.EARPHONES,
            onCategoryClick = navigateToCategory,
            onProductCLick = navigateToProduct
        )
    }
}

@Serializable
data class ProductDetailsRoute(val productId: Int)

fun NavController.navigateToProductDetails(
    productId: Int,
    navOptions: NavOptions
) {
    navigate(route = ProductDetailsRoute(productId),navOptions)
}

fun NavGraphBuilder.productDetailsScreen(
    isCompat: Boolean,
    onOtherProductClick: (productId: Int) -> Unit,
    onCategoryClick: (category: Category) -> Unit
) {
    composable<ProductDetailsRoute> { backStackEntry ->
        val id = backStackEntry.arguments?.getInt("productId") ?: 0
        ProductDetailsScreen(
            productId = id,
            isCompat = isCompat,
            onAddToCartClick = { _, _ -> },
            onOtherProductClick = onOtherProductClick,
            onCategoryClick = onCategoryClick
        )
    }
}
