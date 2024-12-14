package com.museady.cmp.ecommerce.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.museady.cmp.ecommerce.core.entity.Category
import com.museady.cmp.ecommerce.designsystem.theme.AppColors
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

/**
 * A composable for displaying a list of product categories.
 * This component is designed to be used throughout the app as part of the design system.
 */
@Composable
fun CategoryList(
    isCompact: Boolean,
    onCategoryClick: (category: Category) -> Unit,
    categories: List<Category> = Category.entries,
    hideCategory: Category? = null,
    modifier: Modifier = Modifier
) {
    if (isCompact) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = modifier
        ) {
            categories.forEach { category ->
                if (hideCategory == category) return@forEach
                CategoryCard(
                    title = stringResource(category.titleRes),
                    imageResource = painterResource(category.drawableRes),
                    onShopClick = { onCategoryClick(category) }
                )
            }
        }
    } else {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = modifier
        ) {
            categories.forEach { category ->
                if (hideCategory == category) return@forEach
                CategoryCard(
                    title = stringResource(category.titleRes),
                    imageResource = painterResource(category.drawableRes),
                    onShopClick = { onCategoryClick(category) },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun CategoryCard(
    title: String,
    imageResource: Painter,
    onShopClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Box(modifier.fillMaxWidth()) {
        Box(
            Modifier
                .height(164.dp)
                .fillMaxWidth()
                .shadow(2.dp, shape = RoundedCornerShape(8.dp), clip = true)
                .background(AppColors.NeutralLight)
                .align(Alignment.BottomCenter)
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CategoryCardImage(
                imageResource = imageResource,
                contentDescription = title
            )
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
            )
            ShopButtonWithTrailingIcon(onShopClick)
        }
    }
}

@Composable
fun CategoryCardImage(
    imageResource: Painter,
    contentDescription: String,
    modifier: Modifier = Modifier
) {
    Image(
        painter = imageResource,
        contentDescription = contentDescription,
        modifier = modifier.size(140.dp),
        contentScale = ContentScale.Inside
    )
}
