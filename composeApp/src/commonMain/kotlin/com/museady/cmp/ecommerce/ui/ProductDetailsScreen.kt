package com.museady.cmp.ecommerce.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.museady.cmp.ecommerce.designsystem.theme.AppColors
import ecommerce_cmp.composeapp.generated.resources.Res
import ecommerce_cmp.composeapp.generated.resources.new_product_description
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ProductHeaderImage(
    imageRes: DrawableResource,
    contentDescription: String? = null,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(imageRes),
        modifier = modifier.fillMaxWidth(),
        contentDescription = contentDescription
    )
}

@Composable
fun ProductTitle(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        title,
        style = MaterialTheme.typography.headlineLarge.copy(
            letterSpacing = 1.sp,
            color = AppColors.PureBlack
        )
    )
}

@Composable
fun ProductDescription(modifier: Modifier = Modifier) {
    Text(
        stringResource(Res.string.new_product_description),
        style = MaterialTheme.typography.bodyMedium,
        textAlign = TextAlign.Center,
        color = AppColors.LightGrey,
        modifier = modifier    )
}

@Composable
fun ProductPrice(price:Int,
                 ) {
    Text(text = "$ $price",
        style = MaterialTheme.typography.titleLarge,
        )
}


