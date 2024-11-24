package com.museady.cmp.ecommerce.designsystem.component

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults.Container
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.museady.cmp.ecommerce.designsystem.theme.AppColors
import com.museady.cmp.ecommerce.designsystem.theme.AppShapes
import ecommerce_cmp.composeapp.generated.resources.Res
import ecommerce_cmp.composeapp.generated.resources.wrong_format
import org.jetbrains.compose.resources.stringResource

@Composable
fun EcommerceTextField(
    title: String,
    value: String,
    placeHolderText: String,
    onValueChange: (String) -> Unit,
    isError: Boolean = false,
    modifier: Modifier = Modifier
) {
    val titleTextColor = if (isError) AppColors.ErrorColor else AppColors.TextDark

    Column(
        modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextFieldHeader(
            text = title,
            color = titleTextColor
        )
        EcommerceBasicTextField(
            text = value,
            isError = isError,
            placeHolderText = placeHolderText,
            onValueChange = onValueChange
        )
    }
}

@Composable
fun TextFieldHeader(
    text: String,
    color: Color
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text,
            color = color,
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            stringResource(Res.string.wrong_format),
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EcommerceBasicTextField(
    text: String,
    placeHolderText: String,
    onValueChange: (String) -> Unit,
    isError: Boolean = false,
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
) {
    val outlineTextFieldColors = OutlinedTextFieldDefaults.colors(
        errorBorderColor = AppColors.ErrorColor,
        focusedBorderColor = AppColors.PrimaryColor,
        unfocusedBorderColor = AppColors.OutlineColor
    )

    BasicTextField(
        value = text,
        singleLine = true,
        interactionSource = interactionSource,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        textStyle = MaterialTheme.typography.titleSmall
    ) { innerTextField ->
        OutlinedTextFieldDefaults.DecorationBox(
            value = text,
            innerTextField = innerTextField,
            enabled = true,
            singleLine = true,
            interactionSource = interactionSource,
            visualTransformation = VisualTransformation.None,
            placeholder = {
                Text(
                    text = placeHolderText,
                    style = MaterialTheme.typography.titleSmall,
                    color = AppColors.AlternativeGrayColor
                )
            },
            container = {
                Container(
                    enabled = true,
                    isError = isError,
                    interactionSource = interactionSource,
                    colors = outlineTextFieldColors,
                    shape = AppShapes.InputFieldCornerShape,
                    focusedBorderThickness = .5.dp,
                    unfocusedBorderThickness = .5.dp,
                )
            }
        )
    }
}
