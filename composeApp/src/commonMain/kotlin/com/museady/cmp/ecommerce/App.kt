package com.museady.cmp.ecommerce

import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.window.core.layout.WindowSizeClass
import com.museady.cmp.ecommerce.designsystem.theme.EcommerceTheme
import com.museady.cmp.ecommerce.ui.EcommerceCatalog

@Composable
fun App(
    windowSizeClass: WindowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
) {
    EcommerceTheme {
        EcommerceCatalog(windowSizeClass)
    }
}