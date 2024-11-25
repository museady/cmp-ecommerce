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
import com.museady.cmp.ecommerce.designsystem.icons.AppIcons
import com.museady.cmp.ecommerce.designsystem.theme.AppColors
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun FilledButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FilledTonalButton(
        onClick = onClick,
        shape = RoundedCornerShape(0.dp),
        modifier = modifier,
        colors = ButtonDefaults.filledTonalButtonColors().copy(
            containerColor = AppColors.PrimaryColor
        )
    ) {
        ButtonText(
            text = text,
            color = AppColors.TextLight
        )
    }
}

@Composable
fun OutLineTextButton(
    text: String,
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
            text = text,
            color = AppColors.TextDark
        )
    }
}

@Composable
fun TextButtonWithTrailingIcon(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TextButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            ButtonText(
                text = text,
                modifier = Modifier.padding(end = 4.dp),
                color = AppColors.AlternativeGrayColor
            )
            Icon(
                imageVector = AppIcons.ArrowForward,
                contentDescription = text,
                tint = AppColors.PrimaryColor,
                modifier = Modifier.size(16.dp)
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
    FilledButton(
        text = "see product",
        onClick = {}
    )
}

@Preview
@Composable
fun PreviewOutLineTextButton() {
    OutLineTextButton(
        text = "see product",
        onClick = {}
    )
}

@Preview
@Composable
fun PreviewTextButtonWithTrailingIcon() {
    TextButtonWithTrailingIcon(
        text = "shop",
        onClick = {}
    )
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
        FilledButton(
            text = "see product",
            onClick = {}
        )
        OutLineTextButton(
            text = "see product",
            onClick = {}
        )
        TextButtonWithTrailingIcon(
            text = "shop",
            onClick = {}
        )
    }
}
