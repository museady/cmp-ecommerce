@file:OptIn(ExperimentalMaterial3Api::class)

package com.museady.cmp.ecommerce.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.museady.cmp.ecommerce.designsystem.theme.AppColors
import ecommerce_cmp.composeapp.generated.resources.Res
import ecommerce_cmp.composeapp.generated.resources.app_name
import ecommerce_cmp.composeapp.generated.resources.cd_top_appbar_cart_button
import ecommerce_cmp.composeapp.generated.resources.cd_top_appbar_menu_button
import ecommerce_cmp.composeapp.generated.resources.ic_cart
import ecommerce_cmp.composeapp.generated.resources.ic_hamburger
import ecommerce_cmp.composeapp.generated.resources.logo
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

private val TopAppBarColors
    @Composable get() = TopAppBarDefaults.topAppBarColors().copy(
        containerColor = AppColors.NeutralDark,
        titleContentColor = AppColors.PureWhite,
        actionIconContentColor = Color.White,
        navigationIconContentColor = Color.White,
    )

@Composable
fun MobileTopAppbar(
    onMenuButtonClick: () -> Unit,
    onCartClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            TopAppBarTitle()
        },
        navigationIcon = {
            NavigationIcon(onMenuButtonClick = onMenuButtonClick)
        },
        actions = {
            TopAppBarCartAction(onCartClick = onCartClick)
        },
        colors = TopAppBarColors,
    )
}

@Composable
fun TabletTopAppbar(
    onMenuButtonClick: () -> Unit,
    onCartClick: () -> Unit
) {
    TopAppBar(
        title = {
            TopAppBarTitle()
        },
        navigationIcon = {
            NavigationIcon(
                onMenuButtonClick = onMenuButtonClick,
                modifier = Modifier.padding(start = 20.dp)
            )
        },
        actions = {
            TopAppBarCartAction(
                onCartClick = onCartClick,
                modifier = Modifier.padding(end = 24.dp)
            )
        },
        colors = TopAppBarColors,
    )
}

@Composable
private fun TopAppBarTitle() {
    Icon(
        painter = painterResource(Res.drawable.logo),
        contentDescription = stringResource(Res.string.app_name),
    )
}

@Composable
private fun NavigationIcon(
    onMenuButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        IconButton(onMenuButtonClick) {
            Icon(
                painter = painterResource(Res.drawable.ic_hamburger),
                contentDescription = stringResource(Res.string.cd_top_appbar_menu_button)
            )
        }
    }
}

@Composable
private fun TopAppBarCartAction(
    onCartClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        IconButton(onCartClick) {
            Icon(
                painter = painterResource(Res.drawable.ic_cart),
                contentDescription = stringResource(Res.string.cd_top_appbar_cart_button)
            )
        }
    }
}
