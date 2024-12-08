package com.museady.cmp.ecommerce.ui

import  androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.museady.cmp.ecommerce.core.entity.Category
import com.museady.cmp.ecommerce.designsystem.component.AppFooter
import com.museady.cmp.ecommerce.designsystem.component.BodyText
import com.museady.cmp.ecommerce.designsystem.component.CategoryList
import com.museady.cmp.ecommerce.designsystem.component.SeeProductFilledButton
import com.museady.cmp.ecommerce.designsystem.component.SeeProductOutlineButton
import com.museady.cmp.ecommerce.designsystem.component.TopAppBarDivider
import com.museady.cmp.ecommerce.designsystem.theme.AppColors
import com.museady.cmp.ecommerce.designsystem.theme.AppShapes.DefaultCardShape
import ecommerce_cmp.composeapp.generated.resources.Res
import ecommerce_cmp.composeapp.generated.resources.mobile_home_image_header
import ecommerce_cmp.composeapp.generated.resources.mobile_home_yx1
import ecommerce_cmp.composeapp.generated.resources.mobile_home_zx7
import ecommerce_cmp.composeapp.generated.resources.mobile_home_zx9
import ecommerce_cmp.composeapp.generated.resources.new_product
import ecommerce_cmp.composeapp.generated.resources.new_product_description
import ecommerce_cmp.composeapp.generated.resources.new_product_name
import ecommerce_cmp.composeapp.generated.resources.pattern_circles
import ecommerce_cmp.composeapp.generated.resources.tablet_home_image_header
import ecommerce_cmp.composeapp.generated.resources.tablet_home_yx1
import ecommerce_cmp.composeapp.generated.resources.tablet_home_zx7
import ecommerce_cmp.composeapp.generated.resources.tablet_home_zx9
import ecommerce_cmp.composeapp.generated.resources.yx1_earphones_name
import ecommerce_cmp.composeapp.generated.resources.zx7_speaker_name
import ecommerce_cmp.composeapp.generated.resources.zx9_speaker_desc
import ecommerce_cmp.composeapp.generated.resources.zx9_speaker_name
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

private fun String.splitTextBySpace() = split(" ").joinToString("\n")

@Composable
fun HomeScreen(
    isCompact: Boolean,
    navigateToCategory: (category: Category) -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    Column(modifier.verticalScroll(scrollState)) {
        TopAppBarDivider(horizontalPadding = if (isCompact) 0.dp else 40.dp)
        NewProductCard(isCompact)
        Spacer(Modifier.height(if (isCompact) 40.dp else 96.dp))
        HomeContent(isCompact = isCompact, navigateToCategory)
        AppFooter(isCompact)
    }
}

@Composable
private fun HomeContent(
    isCompact: Boolean,
    onCategoryClick: (category: Category) -> Unit
) {
    val horizontal = if (isCompact) 24.dp else 40.dp
    val vertical = if (isCompact) 120.dp else 96.dp
    Column(modifier = Modifier.padding(horizontal = horizontal)) {
        CategoryList(isCompact = isCompact, onCategoryClick = onCategoryClick)
        Spacer(Modifier.height(vertical))
        FeaturedProducts(isCompact = isCompact)
        Spacer(Modifier.height(vertical))
    }
}

@Composable
fun NewProductCard(
    isCompact: Boolean,
    onSeeProductClick: () -> Unit = {},
) {
    val imageRes =
        if (isCompact) Res.drawable.mobile_home_image_header else Res.drawable.tablet_home_image_header

    Box(modifier = Modifier.fillMaxWidth()) {
        NewProductImage(imageRes = imageRes)
        GradientBackgroundOverlay()
        NewProductContent(isCompact = isCompact, onSeeProductClick = onSeeProductClick)
    }
}

@Composable
fun BoxScope.GradientBackgroundOverlay() {
    val gradient = Brush.verticalGradient(
        colors = listOf(
            AppColors.NeutralDark,
            Color.Transparent,
            Color.Transparent
        )
    )

    Box(Modifier.matchParentSize().alpha(1f).background(gradient))
}

@Composable
fun NewProductImage(imageRes: DrawableResource) =
    Box(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(imageRes),
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth,
            contentDescription = stringResource(Res.string.new_product_description),
        )
    }

@Composable
fun FeaturedProducts(isCompact: Boolean) {
    val verticalArrangement =
        if (isCompact) Arrangement.spacedBy(24.dp) else Arrangement.spacedBy(32.dp)

    Column(verticalArrangement = verticalArrangement) {
        FeaturedSpeakerHighlightCard(isCompact)
        FeaturedSpeakerCompactCard(isCompact)
        FeaturedEarphoneCard(isCompact)
    }
}

@Composable
fun BoxScope.NewProductContent(
    isCompact: Boolean,
    onSeeProductClick: () -> Unit
) {
    with(MaterialTheme.typography) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                stringResource(Res.string.new_product),
                style = titleMedium,
                color = AppColors.MediumGrey
            )
            Spacer(Modifier.height(if (isCompact) 16.dp else 24.dp))
            Text(
                stringResource(Res.string.new_product_name),
                style = if (isCompact) displaySmall else displayLarge,
                color = AppColors.PureWhite
            )
            Spacer(Modifier.height(24.dp))
            BodyText(
                stringResource(Res.string.new_product_description),
                color = AppColors.LightGrey,
                modifier = Modifier.widthIn(max = if (isCompact) 328.dp else 348.dp)
            )
            Spacer(Modifier.height(if (isCompact) 28.dp else 40.dp))
            SeeProductFilledButton(onClick = onSeeProductClick)
        }
    }
}

@Composable
private fun FeaturedSpeakerHighlightCard(isCompact: Boolean) {
    val productImageRes =
        if (isCompact) Res.drawable.mobile_home_zx9 else Res.drawable.tablet_home_zx9
    val imageTopPadding = if (isCompact) 52.dp else 56.dp
    val circlesScale = if (isCompact) 1.5f else 1.4f
    val titleMarginTop = if (isCompact) 32.dp else 64.dp
    val titleTextStyle = if (isCompact) MaterialTheme.typography.displaySmall.copy(
        fontSize = 36.sp,
        lineHeight = 40.sp,
        letterSpacing = 1.29.sp
    ) else MaterialTheme.typography.displayLarge
    val descMaxWidth = if (isCompact) 280.dp else 348.dp
    val buttonMarginTop = if (isCompact) 24.dp else 40.dp
    val bottomMargin = if (isCompact) 56.dp else 64.dp

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .clip(DefaultCardShape)
            .background(color = AppColors.Primary)
    ) {
        val (productImage, patternCirclesImage, productName, productDesc, seeProductButton) =
            createRefs()

        // Product Image
        Image(
            painter = painterResource(productImageRes),
            contentDescription = stringResource(Res.string.zx9_speaker_name),
            modifier = Modifier
                .fillMaxWidth(.5f)
                .constrainAs(productImage) {
                    top.linkTo(parent.top, margin = imageTopPadding)
                    centerHorizontallyTo(parent)
                }
        )

        // Pattern Circles Image
        Image(
            painter = painterResource(Res.drawable.pattern_circles),
            modifier = Modifier
                .scale(circlesScale)
                .constrainAs(patternCirclesImage) {
                    centerTo(productImage)
                },
            contentDescription = null,
        )

        // Product Name (Title)
        Text(
            text = stringResource(Res.string.zx9_speaker_name).splitTextBySpace(),
            style = titleTextStyle,
            color = AppColors.PureWhite,
            minLines = 2,
            textAlign = TextAlign.Center,
            modifier = Modifier.constrainAs(productName) {
                top.linkTo(productImage.bottom, margin = titleMarginTop)
                centerHorizontallyTo(parent)
            }
        )

        // Product Description
        Text(
            text = stringResource(Res.string.zx9_speaker_desc),
            textAlign = TextAlign.Center,
            color = AppColors.NeutralLight,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier
                .widthIn(max = descMaxWidth)
                .padding(bottom = buttonMarginTop)
                .constrainAs(productDesc) {
                    top.linkTo(productName.bottom, margin = 24.dp)
                    centerHorizontallyTo(parent)
                }
        )

        SeeProductFilledButton(
            onClick = {},
            containerColor = AppColors.PureBlack,
            modifier = Modifier.constrainAs(seeProductButton) {
                top.linkTo(productDesc.bottom, margin = buttonMarginTop)
                bottom.linkTo(parent.bottom, margin = bottomMargin)
                centerHorizontallyTo(parent)
            }
        )
    }
}

@Composable
fun FeaturedSpeakerCompactCard(isCompact: Boolean) {
    val imageRes = if (isCompact) Res.drawable.mobile_home_zx7 else Res.drawable.tablet_home_zx7
    val contentStartPadding = if (isCompact) 24.dp else 64.dp
    val textBottomPadding = if (isCompact) 24.dp else 32.dp
    val productName = stringResource(Res.string.zx7_speaker_name)
    Box(
        Modifier
            .fillMaxWidth()
            .clip(DefaultCardShape)
    ) {
        Image(
            painter = painterResource(imageRes),
            contentDescription = productName,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth()
        )

        Column(Modifier.align(Alignment.CenterStart).padding(start = contentStartPadding)) {
            Text(
                productName,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(bottom = textBottomPadding)
            )

            SeeProductOutlineButton({})
        }
    }
}

@Composable
private fun FeaturedEarphoneCard(isCompact: Boolean) {
    val productName = stringResource(Res.string.yx1_earphones_name)

    if (isCompact) {
        Column(Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(Res.drawable.mobile_home_yx1),
                contentDescription = productName,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Column(
                Modifier.fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp)).background(AppColors.NeutralLight)
            ) {
                Text(
                    productName,
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.padding(bottom = 24.dp, top = 40.dp, start = 24.dp)
                )

                SeeProductOutlineButton(
                    {},
                    modifier = Modifier.padding(bottom = 40.dp, start = 24.dp)
                )
            }
        }
    } else {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.height(IntrinsicSize.Min)

        ) {
            Image(
                painter = painterResource(Res.drawable.tablet_home_yx1),
                contentDescription = productName,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .clip(DefaultCardShape)
                    .weight(1f)
            )

            Column(
                Modifier
                    .fillMaxHeight()
                    .clip(DefaultCardShape)
                    .background(AppColors.NeutralLight)
                    .weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    productName,
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.padding(start = 40.dp, bottom = 32.dp)
                )

                SeeProductOutlineButton({})
            }
        }
    }
}
//endregion
