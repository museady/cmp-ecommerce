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
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val titleTextId: StringResource,
    val route: KClass<*>,
    val baseRoute: KClass<*> = route,
) {
    HOME(
        selectedIcon = AppIcons.HomeFilled,
        unselectedIcon = AppIcons.Home,
        titleTextId = Res.string.home_title,
        route = HomeRoute::class
    ),
    HEADPHONES(
        selectedIcon = AppIcons.HeadphonesFilled,
        unselectedIcon = AppIcons.Headphones,
        titleTextId = Res.string.headphones_title,
        route = HeadphonesRoute::class
    ),
    SPEAKERS(
        selectedIcon = AppIcons.SpeakerFilled,
        unselectedIcon = AppIcons.Speakers,
        titleTextId = Res.string.speakers_title,
        route = SpeakersRoute::class
    ),
    EARPHONES(
        selectedIcon = AppIcons.EarphoneFilled,
        unselectedIcon = AppIcons.Earphone,
        titleTextId = Res.string.earphones_title,
        route = EarphonesRoute::class
    ),
}
