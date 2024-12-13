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

/**
 * Type for the top level destinations in the application. Contains metadata about the destination
 * that is used in the top app bar and common navigation UI.
 *
 * @param selectedIcon The icon to be displayed in the navigation UI when this destination is
 * selected.
 * @param unselectedIcon The icon to be displayed in the navigation UI when this destination is
 * not selected.
 * @param titleTextId Text that is displayed on the top app bar.
 * @param route The route to use when navigating to this destination.
 * there is a single destination in that section of the app (no nested destinations).
 */
enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val titleTextId: StringResource,
    val route : KClass<*>,
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
