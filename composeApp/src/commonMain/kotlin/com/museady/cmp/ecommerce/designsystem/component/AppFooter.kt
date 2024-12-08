package com.museady.cmp.ecommerce.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.museady.cmp.ecommerce.designsystem.theme.AppColors
import com.museady.cmp.ecommerce.designsystem.theme.AppShapes.DefaultCardShape
import ecommerce_cmp.composeapp.generated.resources.Res
import ecommerce_cmp.composeapp.generated.resources.footer_word_to_highlight
import ecommerce_cmp.composeapp.generated.resources.home_best_gear_desc
import ecommerce_cmp.composeapp.generated.resources.home_best_gear_title
import ecommerce_cmp.composeapp.generated.resources.mobile_home_best_gear
import ecommerce_cmp.composeapp.generated.resources.tablet_home_best_gear
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

// Highlights the specified word in the text with a given color.
private fun highlightWordInText(
    fullText: String,
    wordToHighlight: String,
    highlightColor: Color
): AnnotatedString {
    return buildAnnotatedString {
        val startIndex = fullText.indexOf(wordToHighlight, ignoreCase = true)
        if (startIndex != -1) {
            // Text before the highlighted word
            append(fullText.substring(0, startIndex))
            // Highlighted word
            withStyle(style = SpanStyle(color = highlightColor)) {
                append(fullText.substring(startIndex, startIndex + wordToHighlight.length))
            }
            // Text after the highlighted word
            append(fullText.substring(startIndex + wordToHighlight.length))
        } else {
            // If no match, append the full text unmodified
            append(fullText)
        }
    }
}

@Composable
fun AppFooter(
    isCompact: Boolean,
) {
    if (isCompact) {
        MobileFooterHeroSection()
    } else {
        TabletFooterHeroSection()
    }
}

@Composable
private fun MobileFooterHeroSection() {
    Column(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeroImage(
            heroImageRes = Res.drawable.mobile_home_best_gear,
            modifier = Modifier.padding(bottom = 40.dp)
        )
        HeroTitle(
            textStyle = MaterialTheme.typography.headlineLarge.copy(letterSpacing = 1.sp),
            modifier = Modifier
                .padding(bottom = 24.dp)
                .widthIn(max = 327.dp)
        )
        HeroDescription(modifier = Modifier.widthIn(max = 327.dp))
    }
}

@Composable
private fun TabletFooterHeroSection() {
    Column(
        modifier = Modifier
            .padding(horizontal = 40.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeroImage(
            heroImageRes = Res.drawable.tablet_home_best_gear,
            modifier = Modifier.padding(bottom = 64.dp)
        )
        HeroTitle(
            textStyle = MaterialTheme.typography.displayMedium,
            modifier = Modifier
                .padding(bottom = 32.dp)
                .widthIn(max = 572.dp)
        )
        HeroDescription(modifier = Modifier.widthIn(max = 573.dp))
    }
}

@Composable
private fun HeroImage(
    heroImageRes: DrawableResource,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(heroImageRes),
        contentDescription = "Best Gear",
        modifier = modifier
            .fillMaxWidth()
            .clip(DefaultCardShape),
        contentScale = ContentScale.FillWidth
    )
}

@Composable
private fun HeroTitle(
    textStyle: TextStyle,
    modifier: Modifier = Modifier
) {
    Text(
        text = highlightWordInText(
            fullText = stringResource(Res.string.home_best_gear_title),
            wordToHighlight = stringResource(Res.string.footer_word_to_highlight),
            highlightColor = AppColors.Primary
        ),
        style = textStyle,
        textAlign = TextAlign.Center,
        modifier = modifier
    )
}

@Composable
private fun HeroDescription(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(Res.string.home_best_gear_desc),
        style = MaterialTheme.typography.labelMedium,
        textAlign = TextAlign.Center,
        modifier = modifier.padding(bottom = 120.dp)
    )
}
