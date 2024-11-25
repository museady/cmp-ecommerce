package com.museady.cmp.ecommerce.designsystem.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.museady.cmp.ecommerce.designsystem.icons.AppIcons
import com.museady.cmp.ecommerce.designsystem.theme.AppColors
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun NumberQuantitySelector(
    value: Int,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier,
        color = AppColors.NeutralLight
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            QuantitySelectorIconButton(
                icon = AppIcons.Remove,
                onClick = onDecrement
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = value.toString(),
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(Modifier.width(8.dp))
            QuantitySelectorIconButton(
                icon = AppIcons.Add,
                onClick = onIncrement
            )
        }
    }
}

@Composable
fun QuantitySelectorIconButton(
    icon: ImageVector,
    onClick: () -> Unit
) {
    IconButton(onClick) {
        Icon(
            icon,
            tint = AppColors.AlternativeGrayColor,
            contentDescription = icon.name,
            modifier = Modifier.size(12.dp)
        )
    }
}

@Preview
@Composable
fun PreviewNumberQuantitySelector() {
    NumberQuantitySelector(
        value = 1,
        onIncrement = {},
        onDecrement = {},
        modifier = Modifier.padding(16.dp)
    )
}
