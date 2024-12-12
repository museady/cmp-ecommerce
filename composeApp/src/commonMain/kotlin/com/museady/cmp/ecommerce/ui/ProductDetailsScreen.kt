package com.museady.cmp.ecommerce.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.museady.cmp.ecommerce.core.entity.Category
import com.museady.cmp.ecommerce.core.entity.DeviceImages
import com.museady.cmp.ecommerce.core.entity.Gallery
import com.museady.cmp.ecommerce.core.entity.Include
import com.museady.cmp.ecommerce.core.entity.OtherProduct
import com.museady.cmp.ecommerce.core.entity.Product
import com.museady.cmp.ecommerce.core.json.loadData
import com.museady.cmp.ecommerce.designsystem.component.BodyText
import com.museady.cmp.ecommerce.designsystem.component.CategoryList
import com.museady.cmp.ecommerce.designsystem.component.FilledButton
import com.museady.cmp.ecommerce.designsystem.component.NewProductText
import com.museady.cmp.ecommerce.designsystem.component.NumberQuantitySelector
import com.museady.cmp.ecommerce.designsystem.component.SeeProductFilledButton
import com.museady.cmp.ecommerce.designsystem.theme.AppColors
import com.museady.cmp.ecommerce.designsystem.theme.AppShapes.DefaultCardShape
import ecommerce_cmp.composeapp.generated.resources.Res
import ecommerce_cmp.composeapp.generated.resources.add_to_cart
import ecommerce_cmp.composeapp.generated.resources.features
import ecommerce_cmp.composeapp.generated.resources.in_the_box
import ecommerce_cmp.composeapp.generated.resources.you_may_also_like
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ProductDetailsScreen(
    productId: Int,
    isCompat: Boolean,
    onAddToCartClick: (productId: Int, quantity: Int) -> Unit,
    modifier: Modifier = Modifier,
    onOtherProductClick: (productId: Int) -> Unit,
    onCategoryClick: (category: Category) -> Unit,
) {
    val scope = rememberCoroutineScope()
    var product by remember { mutableStateOf(Product()) }

    LaunchedEffect(Unit) {
        scope.launch {
            product = loadData().first { it.id == productId }
        }
    }
    var quantity by remember { mutableIntStateOf(0) }

    if (product.id != 0)
        Column(
            modifier = modifier
                .padding(horizontal = if (isCompat) 24.dp else 40.dp)
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            ProductDetailsHeader(
                isCompat = isCompat,
                productImages = product.image,
                productTitle = product.name,
                productDescription = product.description,
                isNewProduct = product.new,
                productPrice = product.price,
                onAddToCartClick = { onAddToCartClick(product.id, quantity) },
                quantity = quantity,
                onQuantityChange = { quantity = it }
            )

            Spacer(Modifier.height(if (isCompat) 88.dp else 120.dp))

            ProductFeaturesSection(
                isCompat = isCompat,
                features = product.features,
            )
            Spacer(Modifier.height(if (isCompat) 88.dp else 120.dp))

            ProductIncludedSection(
                includes = product.includes,
                isCompat = isCompat
            )

            Spacer(Modifier.height(if (isCompat) 88.dp else 120.dp))

            ProductGallery(
                isCompat = isCompat,
                gallery = product.gallery
            )

            Spacer(Modifier.height(120.dp))

            OtherProductsSection(
                isCompat = isCompat,
                otherProducts = product.others,
                onSeeOtherProductClick = onOtherProductClick
            )

            CategoryList(
                isCompact = isCompat,
                onCategoryClick = onCategoryClick,
                modifier = Modifier.padding(vertical = 120.dp)
            )
        }
}

@Composable
fun ProductDetailsHeader(
    isCompat: Boolean,
    productImages: DeviceImages,
    productTitle: String,
    productDescription: String,
    productPrice: Int,
    isNewProduct: Boolean,
    onAddToCartClick: () -> Unit,
    quantity: Int,
    onQuantityChange: (Int) -> Unit,
) {
    if (isCompat)
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier.padding(bottom = 24.dp)
        ) {
            ProductHeaderImage(
                findImageByName(productImages.mobile),
                contentDescription = productTitle
            )
            if (isNewProduct) {
                Spacer(Modifier.height(4.dp))
                NewProductText()
            }
            ProductTitle(productTitle, modifier = Modifier)
            BodyText(
                text = productDescription,
                textAlign = TextAlign.Start,
                modifier = Modifier.widthIn(max = 320.dp)
            )
            ProductPrice(productPrice)
            ProductQuantityWithAddToCart(quantity, onQuantityChange, onAddToCartClick)
        }
    else
        Row(
            Modifier.height(IntrinsicSize.Min)
        ) {
            ProductHeaderImage(
                findImageByName(productImages.tablet),
                modifier = Modifier.weight(.4f)
            )

            Spacer(Modifier.weight(.1f))
            Column(
                Modifier.weight(.5f).fillMaxHeight(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                if (isNewProduct) {
                    NewProductText()
                    Spacer(Modifier.height(16.dp))
                }
                ProductTitle(productTitle)
                Spacer(Modifier.height(32.dp))
                BodyText(productDescription, textAlign = TextAlign.Start)
                Spacer(Modifier.height(32.dp))
                ProductPrice(productPrice)
                Spacer(Modifier.height(32.dp))
                ProductQuantityWithAddToCart(quantity, onQuantityChange, onAddToCartClick)
            }
        }
}

@Composable
fun ProductHeaderImage(
    imageRes: DrawableResource,
    contentDescription: String? = null,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(imageRes),
        modifier = modifier.fillMaxWidth()
            .clip(DefaultCardShape),
        contentDescription = contentDescription,
        contentScale = ContentScale.FillWidth,
    )
}

@Composable
fun ProductTitle(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        title.uppercase(),
        style = MaterialTheme.typography.headlineLarge.copy(
            letterSpacing = 1.sp,
            color = AppColors.PureBlack
        ),
        modifier = modifier
    )
}

@Composable
fun ProductPrice(
    price: Int,
) {
    Text(
        text = "$ $price",
        style = MaterialTheme.typography.titleLarge,
    )
}

@Composable
fun ProductQuantityWithAddToCart(
    orderedQuantity: Int,
    onQuantityChange: (Int) -> Unit,
    onAddToCartClick: () -> Unit
) {
    Row {
        NumberQuantitySelector(
            orderedQuantity,
            onIncrement = { onQuantityChange(orderedQuantity + 1) },
            onDecrement = { onQuantityChange(orderedQuantity - 1) })
        Spacer(Modifier.width(16.dp))
        AddToCartFilledButton(onAddToCartClick)
    }
}

@Composable
fun ProductFeaturesSection(
    isCompat: Boolean,
    features: String,
) {
    val contentSpacing = if (isCompat) 24.dp else 32.dp

    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(contentSpacing)
    ) {
        ProductSectionTitleText(Res.string.features, isCompat = false)
        BodyText(features, textAlign = TextAlign.Start)
    }
}

@Composable
fun ProductIncludedSection(
    includes: List<Include>,
    isCompat: Boolean
) {

    if (isCompat) {
        Column {
            ProductSectionTitleText(
                titleRes = Res.string.in_the_box,
                isCompat = isCompat
            )
            Spacer(Modifier.height(24.dp))
            ProductIncludedItems(includes)
        }
    } else {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            ProductSectionTitleText(
                titleRes = Res.string.in_the_box,
                isCompat = isCompat,
                modifier = Modifier.weight(.5f)
            )
            ProductIncludedItems(
                includes,
                modifier = Modifier.weight(.5f)
            )
        }
    }
}

@Composable
fun ProductIncludedItems(
    includes: List<Include>,
    modifier: Modifier = Modifier
) {
    val textStyle = MaterialTheme.typography.labelMedium
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        includes.forEach {
            Row(
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                CompositionLocalProvider(LocalTextStyle provides textStyle) {
                    Text(text = "${it.quantity}x", color = AppColors.Primary)
                    Text(text = it.item)
                }
            }
        }
    }
}

@Composable
fun ProductGallery(
    isCompat: Boolean,
    gallery: Gallery
) {
    if (isCompat) {
        Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
            val images = listOf(gallery.first, gallery.second, gallery.third)

            images.forEach {
                val imageRes = findImageByName(if (isCompat) it.mobile else it.tablet)
                ProductHeaderImage(imageRes)
            }
        }
    } else {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            // Column for the first two images
            Column(
                verticalArrangement = Arrangement.spacedBy(26.dp),
                modifier = Modifier
                    .weight(0.4f) // Controls width of the column
                    .fillMaxHeight()
            ) {
                ProductHeaderImage(findImageByName(gallery.first.tablet))
                ProductHeaderImage(findImageByName(gallery.second.tablet))
            }


            Spacer(Modifier.width(18.dp))

            // Single image in the row
            ProductHeaderImage(
                findImageByName(gallery.third.tablet),
                modifier = Modifier
                    .weight(0.57f) // Controls width of the image
            )
        }
    }
}

@Composable
fun OtherProductsSection(
    isCompat: Boolean,
    otherProducts: List<OtherProduct>,
    onSeeOtherProductClick: (id: Int) -> Unit,
) {
    Column {
        Box(
            Modifier
                .fillMaxWidth()
                .padding(bottom = if (isCompat) 40.dp else 56.dp),
            contentAlignment = Alignment.Center
        ) {
            ProductSectionTitleText(
                isCompat = isCompat,
                titleRes = Res.string.you_may_also_like
            )
        }
        if (isCompat) {
            Column(verticalArrangement = Arrangement.spacedBy(56.dp)) {
                otherProducts.forEach { product ->
                    OtherProductCard(product, isCompat, { onSeeOtherProductClick(product.id) })
                }
            }
        } else {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                otherProducts.forEach { product ->
                    OtherProductCard(
                        product,
                        isCompat,
                        { onSeeOtherProductClick(product.id) },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }

}

@Composable
fun OtherProductCard(
    otherProduct: OtherProduct,
    isCompat: Boolean,
    onSeeProductClick: (id: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val imageRes = if (isCompat) otherProduct.image.mobile else otherProduct.image.tablet

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(32.dp),
        modifier = modifier
    ) {
        ProductHeaderImage(findImageByName(imageRes))
        Text(
            otherProduct.name,
            style = MaterialTheme.typography.headlineMedium.copy(letterSpacing = 1.71.sp)
        )
        SeeProductFilledButton({ onSeeProductClick(otherProduct.id) })
    }
}

@Composable
fun ProductSectionTitleText(
    titleRes: StringResource,
    isCompat: Boolean,
    modifier: Modifier = Modifier
) {
    val style = with(MaterialTheme.typography) {
        if (isCompat) headlineMedium.copy(letterSpacing = .86.sp) else displaySmall
    }

    Text(stringResource(titleRes), style = style, modifier = modifier)
}

@Composable
fun AddToCartFilledButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = AppColors.Primary
) {
    FilledButton(
        textStringRes = Res.string.add_to_cart,
        onClick = onClick,
        modifier = modifier,
        containerColor = containerColor,
    )
}
