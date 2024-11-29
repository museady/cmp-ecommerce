package com.museady.cmp.ecommerce

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import com.museady.cmp.ecommerce.designsystem.component.MobileTopAppbar
import com.museady.cmp.ecommerce.designsystem.component.TabletTopAppbar
import com.museady.cmp.ecommerce.designsystem.theme.AppColors
import com.museady.cmp.ecommerce.designsystem.theme.EcommerceTheme
import com.museady.cmp.ecommerce.ui.EcommerceCatalog

@Composable
fun App(
    windowSizeClass: WindowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
) {
    EcommerceTheme {
        Scaffold(
            Modifier
                .fillMaxSize()
                .background(AppColors.NeutralDark),
            topBar = {
                if (windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.COMPACT) {
                    MobileTopAppbar({}, {})
                } else {
                    TabletTopAppbar({}, {})
                }
            }
        ) { contentPadding ->
            Surface(
                color = AppColors.Background,
                modifier = Modifier.padding(contentPadding)
            ) {
                EcommerceCatalog(windowSizeClass)
            }
        }
    }
}
