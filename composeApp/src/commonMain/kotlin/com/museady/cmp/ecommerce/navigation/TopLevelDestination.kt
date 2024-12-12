package com.museady.cmp.ecommerce.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.museady.cmp.ecommerce.designsystem.icons.AppIcons
import ecommerce_cmp.composeapp.generated.resources.Res
import ecommerce_cmp.composeapp.generated.resources.earphones_title
import ecommerce_cmp.composeapp.generated.resources.headphones_title
import ecommerce_cmp.composeapp.generated.resources.home_title
import ecommerce_cmp.composeapp.generated.resources.speakers_title
import org.jetbrains.compose.resources.StringResource
import kotlin.reflect.KClass

enum class TopLevelDestination(
    val route: KClass<*>,
) {
    HOME(
        route = HomeRoute::class
    ),
    HEADPHONES(

        route = CategoryRoute::class
    ),
    SPEAKERS(


        route = CategoryRoute::class
    ),
    EARPHONES(

        route = CategoryRoute::class
    ),
}
