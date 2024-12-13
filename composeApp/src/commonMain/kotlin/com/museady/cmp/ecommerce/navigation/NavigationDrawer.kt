package com.museady.cmp.ecommerce.navigation

import androidx.compose.material3.DrawerState
import androidx.compose.material3.NavigationDrawerItemColors
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.museady.cmp.ecommerce.designsystem.theme.AppColors

@Composable
fun AppModalNavigationDrawer(
    drawerState: DrawerState,
    drawerContent:@Composable (NavigationDrawerItemColors) -> Unit,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    val navigationDrawerItemColors = NavigationDrawerItemDefaults.colors(
        selectedIconColor = AppColors.Primary,
        unselectedIconColor = AppColors.LightGrey,
        selectedTextColor = AppColors.Primary,
        unselectedTextColor = AppColors.LightGrey,
        selectedContainerColor = AppColors.NeutralLight,
        unselectedContainerColor = Color.Transparent
    )

    androidx.compose.material3.ModalNavigationDrawer(
        drawerContent = {
        drawerContent(navigationDrawerItemColors)
        },
        drawerState = drawerState,
        modifier = modifier
    ) {
        content()
    }
}
