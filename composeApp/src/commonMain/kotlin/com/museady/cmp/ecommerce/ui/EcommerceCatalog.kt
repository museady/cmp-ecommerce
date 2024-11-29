package com.museady.cmp.ecommerce.ui

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
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
import ecommerce_cmp.composeapp.generated.resources.Res
import ecommerce_cmp.composeapp.generated.resources.see_product_button_text
import ecommerce_cmp.composeapp.generated.resources.shop_button
import org.jetbrains.compose.resources.stringResource

@Composable
fun EcommerceCatalog(
    windowSizeClass: WindowSizeClass,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    val appContentHorizontalPadding =
        if (windowSizeClass.windowWidthSizeClass == COMPACT) 20.dp else 40.dp

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                horizontal = appContentHorizontalPadding,
            )
            .scrollable(scrollState, Orientation.Vertical),
    ) {
        Spacer(Modifier.height(16.dp))
        CategoryList(
            windowSizeClass.windowWidthSizeClass == COMPACT,
            {},
        )

        Spacer(Modifier.height(16.dp))
        OutLineTextButton(stringResource(Res.string.see_product_button_text), {})
        Spacer(Modifier.height(16.dp))
        FilledButton(stringResource(Res.string.see_product_button_text), {})
        Spacer(Modifier.height(16.dp))
        TextButtonWithTrailingIcon(stringResource(Res.string.shop_button), {})
    }
}
