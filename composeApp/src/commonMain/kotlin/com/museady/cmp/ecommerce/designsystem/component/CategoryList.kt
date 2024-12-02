package com.museady.cmp.ecommerce.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
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
import com.museady.cmp.ecommerce.designsystem.theme.AppColors
import ecommerce_cmp.composeapp.generated.resources.Res
import ecommerce_cmp.composeapp.generated.resources.cateogry_thumbnail_earphones
import ecommerce_cmp.composeapp.generated.resources.cateogry_thumbnail_headphones
import ecommerce_cmp.composeapp.generated.resources.cateogry_thumbnail_speakers
import ecommerce_cmp.composeapp.generated.resources.earphones
import ecommerce_cmp.composeapp.generated.resources.headphones
import ecommerce_cmp.composeapp.generated.resources.speakers
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

//Placeholder to provide categories this will be fetched later from firebase
val availableCategories = listOf(
    Pair(
        Res.string.headphones,
        Res.drawable.cateogry_thumbnail_headphones
    ),
    Pair(
        Res.string.speakers,
        Res.drawable.cateogry_thumbnail_speakers
    ),
    Pair(
        Res.string.earphones,
        Res.drawable.cateogry_thumbnail_earphones
    )
)

/**
 * A composable for displaying a list of product categories.
 * This component is designed to be used throughout the app as part of the design system.
 */
@Composable
fun CategoryList(
    isCompact: Boolean,
    onShopClick: (index: Int) -> Unit,
    modifier: Modifier = Modifier,
    categories: List<Pair<StringResource, DrawableResource>> = availableCategories
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(if (isCompact) 1 else 3),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier.height(if (isCompact) 700.dp else 220.dp)
    ) {
        itemsIndexed(categories) { index, category ->
            CategoryCard(
                title = stringResource(category.first),
                imageResource = painterResource(category.second),
                onShopClick = { onShopClick(index) }
            )
        }
    }
}

@Composable
fun CategoryCard(
    title: String,
    imageResource: Painter,
    onShopClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier
            .height(223.dp)
            .padding(bottom = 4.dp)
            .fillMaxWidth()
    ) {
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
            CategoryCardImage(imageResource = imageResource)
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
            )
            ShopButtonWithTrailingIcon(onClick = onShopClick)
        }
    }
}

@Composable
fun CategoryCardImage(
    imageResource: Painter,
    modifier: Modifier = Modifier
) {
    Image(
        painter = imageResource,
        contentDescription = null,
        modifier = modifier.size(140.dp),
        contentScale = ContentScale.Inside
    )
}

@Preview
@Composable
fun PreviewCategoryCard() {
    CategoryCard(
        imageResource = painterResource(Res.drawable.cateogry_thumbnail_speakers),
        title = "Headphones",
        onShopClick = {}
    )
}
