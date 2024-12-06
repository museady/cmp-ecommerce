package com.museady.cmp.ecommerce.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.museady.cmp.ecommerce.core.entity.Category
import com.museady.cmp.ecommerce.ui.CategoryScreen
import com.museady.cmp.ecommerce.ui.HomeScreen
import kotlinx.serialization.Serializable

@Serializable
data object HomeRoute

fun NavController.navigateHome(navOptions: NavOptions) = navigate(route = HomeRoute, navOptions)

fun NavGraphBuilder.homeScreen(
    isCompat: Boolean,
    navigateToCategory: (category: Category) -> Unit
) {
    composable<HomeRoute> {
        HomeScreen(isCompat, navigateToCategory)
    }
}

@Serializable
object HeadphonesRoute

fun NavController.navigateToHeadphones(navOptions: NavOptions? = null) =
    navigate(route = HeadphonesRoute, navOptions)

fun NavGraphBuilder.headphoneScreen(
    isCompat: Boolean,
) {
    composable<HeadphonesRoute> {
        CategoryScreen(
            isCompat,
            Category.HEADPHONES
        )
    }
}

@Serializable
object SpeakersRoute

fun NavController.navigateToSpeakers(navOptions: NavOptions? = null) =
    navigate(route = SpeakersRoute, navOptions)

fun NavGraphBuilder.speakersScreen(
    isCompat: Boolean,
) {
    composable<SpeakersRoute> {
        CategoryScreen(
            isCompat,
            Category.SPEAKRS
        )
    }
}

@Serializable
object EarphonesRoute

fun NavController.navigateToEarphones(navOptions: NavOptions? = null) =
    navigate(route = EarphonesRoute, navOptions)

fun NavGraphBuilder.earphonesScreen(
    isCompat: Boolean,
) {
    composable<EarphonesRoute> {
        CategoryScreen(
            isCompat,
            Category.EARPHONES
        )
    }
}
