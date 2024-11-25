package com.museady.cmp.ecommerce.designsystem.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.museady.cmp.ecommerce.designsystem.theme.AppColors
import com.museady.cmp.ecommerce.designsystem.theme.AppShapes
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun RadioSelectionGroup(
    radioOptions: List<String>,
    selectedOptionIndex: Int,
    onSelectClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier.selectableGroup(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        radioOptions.forEachIndexed { index, text ->

            RadioSelectionField(
                selected = selectedOptionIndex == index,
                text = text,
                onclick = { onSelectClick(index) }
            )
        }
    }
}

@Composable
private fun RadioSelectionField(
    selected: Boolean,
    onclick: () -> Unit,
    text: String
) {
    val borderColor = if (selected) AppColors.PrimaryColor else AppColors.OutlineColor

    Box(
        Modifier
            .fillMaxWidth()
            .height(56.dp)
            .border((.5).dp, borderColor, shape = AppShapes.InputFieldCornerShape),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .selectable(
                    selected = selected,
                    onClick = onclick,
                    role = Role.RadioButton
                )
        ) {
            RadioButton(
                selected = selected,
                onClick = onclick,
                modifier = Modifier.padding(vertical = 4.dp),
                colors = RadioButtonDefaults.colors(
                    selectedColor = AppColors.PrimaryColor,
                    unselectedColor = AppColors.OutlineColor,
                )
            )
            Text(
                text,
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}

@Preview
@Composable
fun PreviewRadioSelectionGroup() {
    RadioSelectionGroup(
        radioOptions = listOf("Option 1", "Option 2", "Option 3"),
        selectedOptionIndex = 0,
        onSelectClick = {},
        modifier = Modifier.padding(16.dp)
    )
}

@Preview
@Composable
fun PreviewRadioSelectionFieldSelected() {
    RadioSelectionField(
        selected = true,
        text = "Selected Option",
        onclick = {}
    )
}

@Preview
@Composable
fun PreviewRadioSelectionFieldUnselected() {
    RadioSelectionField(
        selected = false,
        text = "Unselected Option",
        onclick = {}
    )
}
