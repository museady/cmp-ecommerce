package com.museady.cmp.ecommerce.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.museady.cmp.ecommerce.core.entity.Category
import com.museady.cmp.ecommerce.designsystem.theme.AppColors
import com.museady.cmp.ecommerce.designsystem.theme.AppShapes.DefaultCardShape
import com.museady.cmp.ecommerce.navigation.TopLevelDestination
import ecommerce_cmp.composeapp.generated.resources.Res
import ecommerce_cmp.composeapp.generated.resources.app_name
import ecommerce_cmp.composeapp.generated.resources.copy_right_txt
import ecommerce_cmp.composeapp.generated.resources.earphones_title
import ecommerce_cmp.composeapp.generated.resources.footer_word_to_highlight
import ecommerce_cmp.composeapp.generated.resources.headphones_title
import ecommerce_cmp.composeapp.generated.resources.home_best_gear_desc
import ecommerce_cmp.composeapp.generated.resources.home_best_gear_title
import ecommerce_cmp.composeapp.generated.resources.home_title
import ecommerce_cmp.composeapp.generated.resources.ic_facebook
import ecommerce_cmp.composeapp.generated.resources.ic_instagram
import ecommerce_cmp.composeapp.generated.resources.ic_twitter
import ecommerce_cmp.composeapp.generated.resources.logo
import ecommerce_cmp.composeapp.generated.resources.mobile_home_best_gear
import ecommerce_cmp.composeapp.generated.resources.mobile_split_after_word
import ecommerce_cmp.composeapp.generated.resources.speakers_title
import ecommerce_cmp.composeapp.generated.resources.tablet_home_best_gear
import ecommerce_cmp.composeapp.generated.resources.tablet_split_after_word
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

// Inserts a newline after the first occurrence of the specified word.
private fun insertNewLineAfterWord(fullText: String, word: String): String {
    return fullText.replaceFirst(word, "$word\n")
}

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
fun Footer(
    isCompact: Boolean,
    navigateToHome: () -> Unit,
    navigateToCategory:(Category) -> Unit,
    browseSocialMediaWebsite: (String) -> Unit,
) {

    if (isCompact) {
        MobileFooter(
            isCompact,
            navigateToHome,
            navigateToCategory,
            browseSocialMediaWebsite,
        )
    } else {
        TabletFooter(
            isCompact,
            navigateToHome,
            navigateToCategory,
            browseSocialMediaWebsite,
        )
    }
}

@Composable
fun MobileFooter(
    isCompact: Boolean,
    navigateToHome: () -> Unit,
    navigateToCategory:(Category) -> Unit,
    browseSocialMediaWebsite: (String) -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        FooterHeroSection(
            isCompact = isCompact,
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(AppColors.NeutralDark)
                .fillMaxWidth()
        ) {
            FooterDividerWithLogo()
            Spacer(Modifier.height(48.dp))
            FooterNavigationContainer(
                isCompact = isCompact,
                navigateToHome,
                navigateToCategory
            )
            Spacer(Modifier.height(32.dp))
            FooterCopyright()
            Spacer(Modifier.height(32.dp))
            FooterSocialMediaPlatforms(
                availableSocialMediaPlatforms,
                browseSocialMediaWebsite,
            )

            Spacer(Modifier.height(32.dp))
        }
    }
}

@Composable
fun TabletFooter(
    isCompact: Boolean,
    navigateToHome: () -> Unit,
    navigateToCategory:(Category) -> Unit,
    browseSocialMediaWebsite: (String) -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        FooterHeroSection(isCompact = isCompact)
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(32.dp),
            modifier = Modifier
                .fillMaxWidth()
                .background(AppColors.NeutralDark)
                .padding(horizontal = 40.dp)
        ) {
            FooterDividerWithLogo()
            Spacer(Modifier.height(4.dp))
            FooterNavigationContainer(
                isCompact,
                navigateToHome,
                navigateToCategory
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                FooterCopyright()
                FooterSocialMediaPlatforms(
                    availableSocialMediaPlatforms,
                    browseSocialMediaWebsite
                )
            }
            Spacer(Modifier.height(32.dp))
        }
    }
}

@Composable
private fun FooterHeroSection(isCompact: Boolean) {
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
            splitAfterWord = stringResource(Res.string.mobile_split_after_word),
            textStyle = MaterialTheme.typography.headlineLarge.copy(letterSpacing = 1.sp),
            modifier = Modifier.padding(bottom = 24.dp)
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
            splitAfterWord = stringResource(Res.string.tablet_split_after_word),
            textStyle = MaterialTheme.typography.displayMedium,
            modifier = Modifier.padding(bottom = 32.dp)
        )
        HeroDescription(modifier = Modifier.widthIn(max = 573.dp))
    }
}

@Composable
fun FooterDividerWithLogo() {
    HorizontalDivider(
        Modifier
            .width(100.dp)
            .padding(bottom = 40.dp),
        color = AppColors.Primary,
        thickness = 3.dp
    )

    Image(
        painter = painterResource(Res.drawable.logo),
        contentDescription = stringResource(Res.string.app_name),
    )
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
    splitAfterWord: String,
    textStyle: TextStyle,
    modifier: Modifier = Modifier
) {
    Text(
        text = highlightWordInText(
            fullText = insertNewLineAfterWord(
                fullText = stringResource(Res.string.home_best_gear_title),
                word = splitAfterWord
            ),
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

// In App Navigation: Home, Headphones, etc.
@Composable
fun FooterNavigationContainer(
    isCompact: Boolean,
    navigateToHome: () -> Unit,
    navigateToCategory:(Category) -> Unit) {
    if (isCompact) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            FooterNavigationItems(navigateToHome,navigateToCategory)
        }
    } else {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            FooterNavigationItems(navigateToHome,navigateToCategory)
        }
    }
}

@Composable
fun FooterNavigationItems(
    navigateToHome: () -> Unit,
    navigateToCategory:(Category) -> Unit) {
    FooterTextButton(
        text = stringResource(Res.string.home_title),
        onClick = { navigateToHome() }
    )
    FooterTextButton(
        text = stringResource(Res.string.headphones_title),
        onClick = { navigateToCategory(Category.HEADPHONES) }
    )
    FooterTextButton(
        text = stringResource(Res.string.speakers_title),
        onClick = { navigateToCategory(Category.SPEAKERS) }
    )
    FooterTextButton(
        text = stringResource(Res.string.earphones_title),
        onClick = {navigateToCategory(Category.EARPHONES) }
    )
}

@Composable
private fun FooterTextButton(
    text: String,
    onClick: () -> Unit,
) = Text(
    text,
    style = MaterialTheme.typography.labelLarge,
    color = AppColors.PureWhite,
    modifier = Modifier.clickable { onClick() }
)


@Composable
fun FooterCopyright() {
    Text(
        stringResource(Res.string.copy_right_txt),
        style = MaterialTheme.typography.labelMedium,
        color = AppColors.MediumGrey,
    )
}

@Composable
fun FooterSocialMediaPlatforms(
    socialMediaLinks: List<SocialMedia>,
    browseSocialMediaWebsite: (String) -> Unit,
) {
    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        socialMediaLinks.forEach { socialMedia ->
            with(socialMedia) {
                SocialMediaIcons(
                    iconRes = iconRes,
                    contentDescription = name,
                    onClick = { browseSocialMediaWebsite(url) }
                )
            }
        }
    }
}

@Composable
fun SocialMediaIcons(
    iconRes: DrawableResource,
    contentDescription: String,
    onClick: () -> Unit,
) = IconButton(onClick) {
    Icon(
        painter = painterResource(iconRes),
        contentDescription = contentDescription,
        tint = AppColors.PureWhite
    )
}

data class SocialMedia(
    val name: String,
    val iconRes: DrawableResource,
    val url: String,
)

val availableSocialMediaPlatforms = listOf(
    SocialMedia(
        name = "Facebook",
        iconRes = Res.drawable.ic_facebook,
        url = "https://www.facebook.com"
    ),
    SocialMedia(
        name = "Twitter",
        iconRes = Res.drawable.ic_twitter,
        url = "https://www.twitter.com"
    ),
    SocialMedia(
        name = "Instagram",
        iconRes = Res.drawable.ic_instagram,
        url = "https://www.instagram.com"
    )
)
