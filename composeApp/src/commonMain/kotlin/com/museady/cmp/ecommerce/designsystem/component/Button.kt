package com.museady.cmp.ecommerce.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.museady.cmp.ecommerce.designsystem.theme.AppColors
import ecommerce_cmp.composeapp.generated.resources.Res
import ecommerce_cmp.composeapp.generated.resources.ic_arrow_right
import ecommerce_cmp.composeapp.generated.resources.see_product_button_text
import ecommerce_cmp.composeapp.generated.resources.shop_button
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SeeProductFilledButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor:Color = AppColors.Primary
) {
    FilledTonalButton(
        onClick = onClick,
        shape = RoundedCornerShape(0.dp),
        modifier = modifier,
        colors = ButtonDefaults.filledTonalButtonColors().copy(
            containerColor = containerColor
        )
    ) {
        ButtonText(
            text = stringResource(Res.string.see_product_button_text),
            color = AppColors.PureWhite
        )
    }
}

@Composable
fun SeeProductOutlineButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedButton(
        onClick = onClick,
        shape = RoundedCornerShape(0.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        modifier = modifier
    ) {
        ButtonText(
            text = stringResource(Res.string.see_product_button_text),
            color = AppColors.PureBlack
        )
    }
}

@Composable
fun ShopButtonWithTrailingIcon(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TextButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            ButtonText(
                text = stringResource(Res.string.shop_button),
                modifier = Modifier.padding(end = 4.dp),
                color = AppColors.AlternativeGray
            )
            Icon(
                painter = painterResource(Res.drawable.ic_arrow_right),
                contentDescription = stringResource(Res.string.shop_button),
                tint = AppColors.Primary,
            )
        }
    }
}

@Composable
private fun ButtonText(
    text: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Text(
        text = text.uppercase(),
        modifier = modifier.padding(8.dp),
        color = color,
        style = MaterialTheme.typography.titleSmall
    )
}

@Preview
@Composable
fun PreviewFilledButton() {
    SeeProductFilledButton({})
}

@Preview
@Composable
fun PreviewOutLineTextButton() {
    SeeProductOutlineButton({})
}

@Preview
@Composable
fun PreviewTextButtonWithTrailingIcon() {
    ShopButtonWithTrailingIcon({})
}

@Preview
@Composable
fun PreviewAllButtons() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SeeProductFilledButton(onClick = {})
        SeeProductOutlineButton(onClick = {})
        ShopButtonWithTrailingIcon(onClick = {})
    }
}
