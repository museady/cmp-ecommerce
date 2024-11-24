package com.museady.cmp.ecommerce.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable


@Composable
fun  EcommerceTheme(
    content: @Composable () -> Unit
)  {
    MaterialTheme(
        typography = AppTypography,
        content = content
    )
}
