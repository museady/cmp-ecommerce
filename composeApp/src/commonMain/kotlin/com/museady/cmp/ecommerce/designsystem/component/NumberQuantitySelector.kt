package com.museady.cmp.ecommerce.designsystem.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.museady.cmp.ecommerce.designsystem.icons.AppIcons
import com.museady.cmp.ecommerce.designsystem.theme.AppColors

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