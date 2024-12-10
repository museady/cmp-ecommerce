package com.museady.cmp.ecommerce.designsystem.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.museady.cmp.ecommerce.designsystem.theme.AppColors
import ecommerce_cmp.composeapp.generated.resources.Res
import ecommerce_cmp.composeapp.generated.resources.new_product
import org.jetbrains.compose.resources.stringResource

@Composable
fun NewProductText(modifier: Modifier = Modifier) {
    Text(
        stringResource(Res.string.new_product),
        style = MaterialTheme.typography.titleMedium,
        color = AppColors.Primary,
        modifier = modifier
    )
}

@Composable
fun BodyText(
    text :String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Center,
    color: Color = AppColors.BodyTextColor
) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelMedium,
        textAlign = textAlign,
        color = color,
        modifier = modifier
    )
}