package com.museady.cmp.ecommerce.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass.Companion.COMPACT
import androidx.window.core.layout.WindowWidthSizeClass.Companion.MEDIUM
import com.museady.cmp.ecommerce.designsystem.component.CategoryList
import com.museady.cmp.ecommerce.designsystem.component.FilledButton
import com.museady.cmp.ecommerce.designsystem.component.OutLineTextButton
import com.museady.cmp.ecommerce.designsystem.component.TextButtonWithTrailingIcon
import com.museady.cmp.ecommerce.designsystem.theme.EcommerceTheme
import ecommerce_cmp.composeapp.generated.resources.Res
import ecommerce_cmp.composeapp.generated.resources.see_product_button_text
import ecommerce_cmp.composeapp.generated.resources.shop_button
import org.jetbrains.compose.resources.stringResource


@Composable
fun EcommerceCatalog(windowSizeClass: WindowSizeClass) {
    EcommerceTheme {
        Surface {
            val contentPadding = WindowInsets
                .systemBars
                .add(WindowInsets(left = 16.dp, top = 16.dp, right = 16.dp, bottom = 16.dp))
                .asPaddingValues()
            Column(
                modifier = Modifier.fillMaxSize().padding(contentPadding),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                CategoryList(
                    windowSizeClass.windowWidthSizeClass == COMPACT,
                    {},
                    modifier = Modifier.padding(
                        horizontal =
                        when (windowSizeClass.windowWidthSizeClass) {
                            COMPACT -> 16.dp
                            MEDIUM -> 40.dp
                            else -> 164.dp
                        },
                    )
                )

                OutLineTextButton(stringResource(Res.string.see_product_button_text), {})
                FilledButton(stringResource(Res.string.see_product_button_text), {})
                TextButtonWithTrailingIcon(stringResource(Res.string.shop_button), {})
            }
        }
    }
}
