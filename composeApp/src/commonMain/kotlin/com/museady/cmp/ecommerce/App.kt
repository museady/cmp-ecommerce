package com.museady.cmp.ecommerce

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import com.museady.cmp.ecommerce.core.entity.Category
import com.museady.cmp.ecommerce.core.entity.Product
import com.museady.cmp.ecommerce.core.json.loadData
import com.museady.cmp.ecommerce.designsystem.component.AudioPhileTopAppBar
import com.museady.cmp.ecommerce.designsystem.component.Footer
import com.museady.cmp.ecommerce.designsystem.theme.AppColors
import com.museady.cmp.ecommerce.designsystem.theme.EcommerceTheme
import com.museady.cmp.ecommerce.navigation.AppModalNavigationDrawer
import com.museady.cmp.ecommerce.navigation.AppNavHost
import com.museady.cmp.ecommerce.navigation.EarphonesRoute
import com.museady.cmp.ecommerce.navigation.HeadphonesRoute
import com.museady.cmp.ecommerce.navigation.NavigationState
import com.museady.cmp.ecommerce.navigation.ProductDetailsRoute
import com.museady.cmp.ecommerce.navigation.SpeakersRoute
import com.museady.cmp.ecommerce.navigation.TopLevelDestination
import com.museady.cmp.ecommerce.navigation.rememberNavigationState
import ecommerce_cmp.composeapp.generated.resources.Res
import ecommerce_cmp.composeapp.generated.resources.logo
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import kotlin.reflect.KClass

/**
 * Main composable function for the app, wrapping the UI with the `EcommerceTheme`.
 * It handles navigation, drawer state.
 *
 * @param navigationState The current navigation state, used to track the current destination.
 * @param windowSizeClass The window size classification, used to adjust layout for different screen sizes.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(
    navigationState: NavigationState = rememberNavigationState(),
    windowSizeClass: WindowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
) {
    EcommerceTheme {
        val currentDestination = navigationState.currentDestination
        val isCompact = windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.COMPACT
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        val scrollState = rememberScrollState()
        val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

        // Data to be loaded later from viewmodel(TODO to be moved to ViewModel)
        var data by remember { mutableStateOf(emptyList<Product>()) }
        LaunchedEffect(Unit) {
            scope.launch {
                data = loadData() // Placeholder loading function
            }
        }

        // Adding a listener to reset scroll state and close the drawer on destination change
        with(navigationState) {
            DisposableEffect(navController) {
                val listener = NavController.OnDestinationChangedListener { _, _, _ ->
                    scope.launch {
                        scrollState.scrollTo(0)
                        if (drawerState.isOpen) drawerState.close()
                    }
                }
                navController.addOnDestinationChangedListener(listener)

                onDispose {
                    navController.removeOnDestinationChangedListener(listener)
                }
            }
        }

        // Navigation Drawer and content UI
        AppModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = { colors ->
                ModalDrawerSheet(
                    drawerContainerColor = AppColors.NeutralDark
                ) {
                    // Drawer content with navigation options
                    Column(
                        modifier = Modifier.padding(horizontal = 16.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        Spacer(Modifier.height(12.dp))
                        Image(
                            painter = painterResource(Res.drawable.logo),
                            contentDescription = null,
                            modifier = Modifier.padding(16.dp),
                        )

                        // Iterate through each top-level destination to create drawer items
                        TopLevelDestination.entries.forEach { destination ->
                            val selected = currentDestination.isRouteInHierarchy(destination.route)
                            NavigationDrawerItem(
                                label = { Text(stringResource(destination.titleTextId)) },
                                selected = selected,
                                onClick = {
                                    navigationState.navigateToTopLevelDestination(destination)
                                },
                                icon = {
                                    val imageVector = if (selected) destination.selectedIcon
                                    else destination.unselectedIcon

                                    Icon(
                                        imageVector = imageVector,
                                        contentDescription = null
                                    )
                                },
                                colors = colors
                            )
                        }
                    }
                }
            },
            content = {
                Scaffold(
                    contentWindowInsets = WindowInsets(0, 0, 0, 0),
                    topBar = {
                        AudioPhileTopAppBar(
                            isCompact = isCompact,
                            openMenuDrawer = {
                                scope.launch {
                                    drawerState.open() // Open drawer when menu icon is clicked
                                }
                            },
                            onCartClick = {},
                            scrollBehavior = scrollBehavior,
                            categoryNameRes = currentDestination.getCategoryNameIfInHierarchy(),
                            scrollState = scrollState,
                            isProductDetailsScreen = currentDestination.isRouteInHierarchy(ProductDetailsRoute::class),
                            onBackClick = { navigationState.navController.popBackStack() }
                        )
                    },
                    modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
                ) { padding ->
                    Column(
                        Modifier
                            .verticalScroll(scrollState)
                            .background(AppColors.Background)
                            .fillMaxSize()
                            .padding(padding)
                            .consumeWindowInsets(padding)
                            .windowInsetsPadding(WindowInsets.safeDrawing),
                    ) {
                        AppNavHost(
                            navigationState = navigationState,
                            isCompact = isCompact,
                        )

                        Footer(
                            isCompact = isCompact,
                            onTopLevelDestinationClick = navigationState::navigateToTopLevelDestination,
                            browseSocialMediaWebsite = {}
                        )
                    }
                }
            }
        )
    }
}

private fun NavDestination?.isRouteInHierarchy(route: KClass<*>) =
    this?.hierarchy?.any {
        it.hasRoute(route)
    } ?: false

private fun NavDestination?.getCategoryNameIfInHierarchy(): StringResource? {
    return when {
        this?.isRouteInHierarchy(HeadphonesRoute::class) == true -> Category.HEADPHONES.titleRes
        this?.isRouteInHierarchy(SpeakersRoute::class) == true -> Category.SPEAKERS.titleRes
        this?.isRouteInHierarchy(EarphonesRoute::class) == true -> Category.EARPHONES.titleRes
        else -> null
    }
}
