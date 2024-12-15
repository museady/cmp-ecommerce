package com.museady.cmp.ecommerce.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.museady.cmp.ecommerce.core.entity.Category
import com.museady.cmp.ecommerce.core.entity.Product
import com.museady.cmp.ecommerce.core.json.loadData
import com.museady.cmp.ecommerce.designsystem.component.BodyText
import com.museady.cmp.ecommerce.designsystem.component.CategoryList
import com.museady.cmp.ecommerce.designsystem.component.NewProductText
import com.museady.cmp.ecommerce.designsystem.component.SeeProductFilledButton
import com.museady.cmp.ecommerce.designsystem.theme.AppColors
import com.museady.cmp.ecommerce.designsystem.theme.AppShapes.DefaultCardShape
import ecommerce_cmp.composeapp.generated.resources.Res
import ecommerce_cmp.composeapp.generated.resources.allDrawableResources
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun CategoryScreen(
    isCompact: Boolean,
    category: Category,
    onCategoryClick: (category: Category) -> Unit,
    onProductCLick: (id: Int) -> Unit,
) {
    val scope = rememberCoroutineScope()
    val categoryTitle = stringResource(category.titleRes)

    //Todo Later to be extract the loading in viewmodel
    var products by remember { mutableStateOf(emptyList<Product>()) }
    LaunchedEffect(Unit) {
        scope.launch {
            products = loadData().filter {
                it.category.contains(categoryTitle, true)
            }.reversed()
        }
    }

    if (products.isNotEmpty()) {
        Column {
            Spacer(Modifier.height(if (isCompact) 64.dp else 120.dp))

            products.forEach { product ->
                ProductCard(
                    isCompact,
                    product,
                    modifier = Modifier
                        .padding(horizontal = if (isCompact) 24.dp else 40.dp)
                        .padding(bottom = 120.dp),
                    onProductCLick = onProductCLick
                )
            }

            CategoryList(
                isCompact,
                onCategoryClick = onCategoryClick,
                hideCategory = category,
                modifier = Modifier
                    .padding(horizontal = if (isCompact) 24.dp else 40.dp)
                    .padding(bottom = 120.dp)
            )
        }
    }
}

@Composable
fun ProductCard(
    isCompact: Boolean,
    product: Product,
    onProductCLick: (id: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val productImageRes =
        if (isCompact) product.categoryImage.mobile else product.categoryImage.tablet
    val productImageDrawableResource = findImageByName(productImageRes)
    val titleTextStyle = with(MaterialTheme.typography) {
        if (isCompact) headlineLarge.copy(letterSpacing = 1.sp) else displayMedium.copy(
            letterSpacing = 1.43.sp
        )
    }
    val productImageBottomPadding = if (isCompact) 32.dp else 52.dp
    val newProductTextBottomPadding = if (isCompact) 24.dp else 16.dp
    val titleBottomPadding = if (isCompact) 24.dp else 32.dp
    val maxTextWidth = if (isCompact) 327.dp else 572.dp

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        ProductImage(
            productImageDrawableResource,
            product.name,
            modifier = Modifier.padding(bottom = productImageBottomPadding)
        )

        if (product.new)
            NewProductText(Modifier.padding(bottom = newProductTextBottomPadding))

        Text(
            product.name,
            style = titleTextStyle,
            color = AppColors.PureBlack,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(bottom = titleBottomPadding)
                .widthIn(max = maxTextWidth)
        )

        BodyText(
            product.description,
            modifier = Modifier
                .padding(bottom = 24.dp)
                .widthIn(max = maxTextWidth),
        )

        SeeProductFilledButton({
            onProductCLick(product.id)
        })
    }
}

@Composable
fun ProductImage(
    imageRes: DrawableResource,
    contentDescription: String,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(imageRes),
        modifier = modifier.fillMaxWidth().clip(DefaultCardShape),
        contentDescription = contentDescription,
        contentScale = ContentScale.FillWidth
    )
}

fun findImageByName(name: String): DrawableResource = Res.allDrawableResources.getValue(name)
