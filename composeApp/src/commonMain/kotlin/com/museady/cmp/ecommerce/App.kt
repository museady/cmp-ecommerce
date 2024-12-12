package com.museady.cmp.ecommerce

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import com.museady.cmp.ecommerce.core.entity.Product
import com.museady.cmp.ecommerce.core.json.loadData
import com.museady.cmp.ecommerce.designsystem.component.AudioPhileTopAppBar
import com.museady.cmp.ecommerce.designsystem.component.Footer
import com.museady.cmp.ecommerce.designsystem.theme.AppColors
import com.museady.cmp.ecommerce.designsystem.theme.EcommerceTheme
import com.museady.cmp.ecommerce.navigation.AppNavHost
import com.museady.cmp.ecommerce.navigation.NavigationState
import com.museady.cmp.ecommerce.navigation.rememberNavigationState
import kotlinx.coroutines.launch

@Composable
fun App(
    navigationState: NavigationState = rememberNavigationState(),
    windowSizeClass: WindowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
) {
    EcommerceTheme {
        val isCompact = windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.COMPACT

        val scrollState = rememberScrollState()
        val scope = rememberCoroutineScope()

        //Todo later tp be loaded from viewmodel
        var data by remember { mutableStateOf(emptyList<Product>()) }
        LaunchedEffect(Unit) {
            scope.launch {
                data = loadData()
            }
        }

        with(navigationState) {
            DisposableEffect(navController) {
                // Listener to reset the scroll state to the top when the destination changes
                val listener = NavController.OnDestinationChangedListener { _, _, _ ->
                    scope.launch {
                        scrollState.scrollTo(0) // Scroll to the top
                    }
                }
                navController.addOnDestinationChangedListener(listener)

                // Cleanup: Remove the listener when the effect is disposed
                onDispose {
                    navController.removeOnDestinationChangedListener(listener)
                }
            }
        }

        Scaffold(
            containerColor = Color.Transparent,
            contentWindowInsets = WindowInsets(0, 0, 0, 0),
            topBar = {
                AudioPhileTopAppBar(isCompact, {}, {})
            },
        ) { padding ->
            Column(
                Modifier
                    .verticalScroll(scrollState)
                    .background(AppColors.PureWhite)
                    .fillMaxSize()
                    .padding(padding)
                    .consumeWindowInsets(padding)
                    .windowInsetsPadding(WindowInsets.safeDrawing),
            ) {
                AppNavHost(
                    navigationState = navigationState,
                    isCompact = isCompact,
                )

                Footer(isCompact = isCompact,
                    navigateToCategory = navigationState::navigateToCategory,
                    navigateToHome = navigationState::navigateToHome,
                    browseSocialMediaWebsite = {}
                )
            }
        }
    }
}
