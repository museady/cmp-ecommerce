package com.museady.cmp.ecommerce.ui

import androidx.compose.runtime.Composable
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass.Companion.COMPACT

@Composable
fun EcommerceCatalog(
    windowSizeClass: WindowSizeClass,
) {
    val isCompact = windowSizeClass.windowWidthSizeClass == COMPACT

    HomeScreen(isCompact)
}
