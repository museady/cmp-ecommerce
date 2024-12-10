@file:OptIn(ExperimentalMaterial3Api::class)

package com.museady.cmp.ecommerce

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import com.museady.cmp.ecommerce.core.entity.Product
import com.museady.cmp.ecommerce.core.json.loadData
import com.museady.cmp.ecommerce.designsystem.component.AudioPhileTopAppBar
import com.museady.cmp.ecommerce.designsystem.theme.AppColors
import com.museady.cmp.ecommerce.designsystem.theme.EcommerceTheme
import com.museady.cmp.ecommerce.navigation.AppNavHost
import com.museady.cmp.ecommerce.navigation.AppNavigationSuiteScaffold
import com.museady.cmp.ecommerce.navigation.NavigationState
import com.museady.cmp.ecommerce.navigation.rememberNavigationState
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import kotlin.reflect.KClass

@Composable
fun App(
    navigationState: NavigationState = rememberNavigationState(),
    windowAdaptiveInfo: WindowAdaptiveInfo = currentWindowAdaptiveInfo(),
    windowSizeClass: WindowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
) {
    val currentDestination = navigationState.currentDestination

    EcommerceTheme {
        val isCompact = windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.COMPACT

        AppNavigationSuiteScaffold(
            navigationSuiteItems = {
                navigationState.topLevelDestinations.forEach { destination ->
                    val selected = currentDestination
                        .isRouteInHierarchy(destination.baseRoute)
                    item(
                        selected = selected,
                        onClick = { navigationState.navigateToTopLevelDestination(destination) },
                        icon = {
                            Icon(
                                imageVector = destination.unselectedIcon,
                                contentDescription = null,
                            )
                        },
                        selectedIcon = {
                            Icon(
                                imageVector = destination.selectedIcon,
                                contentDescription = null,
                            )
                        },
                        label = {
                            Text(
                                stringResource(destination.titleTextId),
                                style = MaterialTheme.typography.labelSmall
                            )
                        },
                    )
                }
            },
            windowAdaptiveInfo = windowAdaptiveInfo,
            currentDestination = navigationState.currentDestination
        ) {
            Scaffold(
                containerColor = Color.Transparent,
                contentWindowInsets = WindowInsets(0, 0, 0, 0),
                topBar = {
                    AudioPhileTopAppBar(isCompact, {}, {})
                },
            ) { padding ->
                Box(
                    Modifier
                        .background(AppColors.PureWhite)
                        .fillMaxSize()
                        .padding(padding)
                        .consumeWindowInsets(padding)
                        .windowInsetsPadding(WindowInsets.safeDrawing),
                ) {
                    val scope = rememberCoroutineScope()
                    var data by remember { mutableStateOf(emptyList<Product>()) }
                    LaunchedEffect(Unit) {
                        scope.launch {
                            data = loadData()
                        }
                    }

                    AppNavHost(
                        navigationState = navigationState,
                        isCompact = isCompact
                    )
                }
            }
        }
    }
}

private fun NavDestination?.isRouteInHierarchy(route: KClass<*>) =
    this?.hierarchy?.any {
        it.hasRoute(route)
    } ?: false
