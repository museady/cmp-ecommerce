package com.museady.cmp.ecommerce.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.museady.cmp.ecommerce.core.entity.Category
import com.museady.cmp.ecommerce.designsystem.icons.AppIcons
import ecommerce_cmp.composeapp.generated.resources.Res
import ecommerce_cmp.composeapp.generated.resources.home
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
        titleTextId = Res.string.home,
        route = HomeRoute::class
    ),
    HEADPHONES(
        selectedIcon = AppIcons.HeadphonesFilled,
        unselectedIcon = AppIcons.Headphones,
        titleTextId = Category.HEADPHONES.nameStringRes,
        route = HeadphonesRoute::class
    ),
    SPEAKERS(
        selectedIcon = AppIcons.SpeakerFilled,
        unselectedIcon = AppIcons.Speakers,
        titleTextId = Category.SPEAKRS.nameStringRes,
        route = SpeakersRoute::class
    ),
    EARPHONES(
        selectedIcon = AppIcons.EarphoneFilled,
        unselectedIcon = AppIcons.Earphone,
        titleTextId = Category.EARPHONES.nameStringRes,
        route = EarphonesRoute::class
    ),
}
