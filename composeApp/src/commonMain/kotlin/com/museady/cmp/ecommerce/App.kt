package com.museady.cmp.ecommerce

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import com.museady.cmp.ecommerce.designsystem.component.AudioPhileTopAppBar
import com.museady.cmp.ecommerce.designsystem.component.Footer
import com.museady.cmp.ecommerce.designsystem.theme.AppColors
import com.museady.cmp.ecommerce.designsystem.theme.EcommerceTheme
import com.museady.cmp.ecommerce.ui.EcommerceCatalog

@Composable
fun App(
    windowSizeClass: WindowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
) {
    EcommerceTheme {
        val isCompact = windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.COMPACT
        Scaffold(
            Modifier
                .fillMaxSize()
                .background(AppColors.NeutralDark),
            topBar = {
                AudioPhileTopAppBar(isCompact, {}, {})
            },
        ) { contentPadding ->
            Surface(
                color = AppColors.Background,
                modifier = Modifier.padding(contentPadding)
            ) {
                val scrollState = rememberScrollState()

                Column(
                    Modifier.verticalScroll(
                        state = scrollState,
                        enabled = true,
                    )
                ) {
                    EcommerceCatalog(windowSizeClass)
                    Footer(isCompact, {}, {}, {}, {}, {})
                }
            }
        }
    }
}
