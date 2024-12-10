//package com.museady.cmp.ecommerce.ui
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.width
//import androidx.compose.material3.HorizontalDivider
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import com.museady.cmp.ecommerce.designsystem.theme.AppColors
//import ecommerce_cmp.composeapp.generated.resources.Res
//import ecommerce_cmp.composeapp.generated.resources.app_name
//import ecommerce_cmp.composeapp.generated.resources.copy_right_txt
//import ecommerce_cmp.composeapp.generated.resources.earphones
//import ecommerce_cmp.composeapp.generated.resources.headphones
//import ecommerce_cmp.composeapp.generated.resources.home
//import ecommerce_cmp.composeapp.generated.resources.ic_facebook
//import ecommerce_cmp.composeapp.generated.resources.ic_instagram
//import ecommerce_cmp.composeapp.generated.resources.ic_twitter
//import ecommerce_cmp.composeapp.generated.resources.logo
//import ecommerce_cmp.composeapp.generated.resources.speakers
//import org.jetbrains.compose.resources.DrawableResource
//import org.jetbrains.compose.resources.painterResource
//import org.jetbrains.compose.resources.stringResource
//
//@Composable
//fun MobileAboutSection(
//    isCompact: Boolean,
//    navigateToHome: () -> Unit,
//    navigateToHeadphones: () -> Unit,
//    navigateToSpeakers: () -> Unit,
//    navigateToEarphones: () -> Unit,
//    browseSocialMediaWebsite: (String) -> Unit,
//) {
//
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            modifier = Modifier
//                .background(AppColors.NeutralDark)
//                .fillMaxWidth()
//        ) {
//            FooterDividerWithLogo()
//            Spacer(Modifier.height(48.dp))
//            FooterNavigationContainer(
//                isCompact = isCompact,
//                navigateToHome = navigateToHome,
//                navigateToHeadphones = navigateToHeadphones,
//                navigateToSpeakers = navigateToSpeakers,
//                navigateToEarphones = navigateToEarphones,
//            )
//            Spacer(Modifier.height(32.dp))
//            FooterCopyright()
//            Spacer(Modifier.height(32.dp))
//            FooterSocialMediaPlatforms(
//                availableSocialMediaPlatforms,
//                browseSocialMediaWebsite,
//            )
//
//            Spacer(Modifier.height(32.dp))
//        }
//
//}
//
//@Composable
//fun TabletAboutSection(
//    isCompact: Boolean,
//    navigateToHome: () -> Unit,
//    navigateToHeadphones: () -> Unit,
//    navigateToSpeakers: () -> Unit,
//    navigateToEarphones: () -> Unit,
//    browseSocialMediaWebsite: (String) -> Unit,
//) {
//        Column(
//            horizontalAlignment = Alignment.Start,
//            verticalArrangement = Arrangement.spacedBy(32.dp),
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(AppColors.NeutralDark)
//                .padding(horizontal = 40.dp)
//        ) {
//            FooterDividerWithLogo()
//            Spacer(Modifier.height(4.dp))
//            FooterNavigationContainer(
//                isCompact,
//                navigateToHome,
//                navigateToHeadphones,
//                navigateToSpeakers,
//                navigateToEarphones,
//            )
//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.SpaceBetween,
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                FooterCopyright()
//                FooterSocialMediaPlatforms(
//                    availableSocialMediaPlatforms,
//                    browseSocialMediaWebsite
//                )
//            }
//    }
//}
//
//@Composable
//private fun FooterDividerWithLogo() {
//    HorizontalDivider(
//        Modifier
//            .width(100.dp)
//            .padding(bottom = 40.dp),
//        color = AppColors.Primary,
//        thickness = 3.dp
//    )
//
//    Image(
//        painter = painterResource(Res.drawable.logo),
//        contentDescription = stringResource(Res.string.app_name),
//    )
//}
//
//// In App Navigation: Home, Headphones, etc.
//@Composable
//fun FooterNavigationContainer(
//    isCompact: Boolean,
//    navigateToHome: () -> Unit,
//    navigateToHeadphones: () -> Unit,
//    navigateToSpeakers: () -> Unit,
//    navigateToEarphones: () -> Unit,
//) {
//    if (isCompact) {
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.spacedBy(16.dp)
//        ) {
//            FooterNavigationItems(
//                navigateToHome,
//                navigateToHeadphones,
//                navigateToSpeakers,
//                navigateToEarphones
//            )
//        }
//    } else {
//        Row(
//            horizontalArrangement = Arrangement.spacedBy(16.dp),
//        ) {
//            FooterNavigationItems(
//                navigateToHome,
//                navigateToHeadphones,
//                navigateToSpeakers,
//                navigateToEarphones
//            )
//        }
//    }
//}
//
//@Composable
//fun FooterNavigationItems(
//    navigateToHome: () -> Unit,
//    navigateToHeadphones: () -> Unit,
//    navigateToSpeakers: () -> Unit,
//    navigateToEarphones: () -> Unit
//) {
//    FooterTextButton(
//        text = stringResource(Res.string.home),
//        onClick = navigateToHome
//    )
//    FooterTextButton(
//        text = stringResource(Res.string.headphones),
//        onClick = navigateToHeadphones
//    )
//    FooterTextButton(
//        text = stringResource(Res.string.speakers),
//        onClick = navigateToSpeakers
//    )
//    FooterTextButton(
//        text = stringResource(Res.string.earphones),
//        onClick = navigateToEarphones
//    )
//}
//
//@Composable
//private fun FooterTextButton(
//    text: String,
//    onClick: () -> Unit,
//) = Text(
//    text,
//    style = MaterialTheme.typography.labelLarge,
//    color = AppColors.PureWhite,
//    modifier = Modifier.clickable { onClick() }
//)
//
//
//@Composable
//fun FooterCopyright() {
//    Text(
//        stringResource(Res.string.copy_right_txt),
//        style = MaterialTheme.typography.labelMedium,
//        color = AppColors.MediumGrey,
//    )
//}
//
//@Composable
//fun FooterSocialMediaPlatforms(
//    socialMediaLinks: List<SocialMedia>,
//    browseSocialMediaWebsite: (String) -> Unit,
//) {
//    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
//        socialMediaLinks.forEach { socialMedia ->
//            with(socialMedia) {
//                SocialMediaIcons(
//                    iconRes = iconRes,
//                    contentDescription = name,
//                    onClick = { browseSocialMediaWebsite(url) }
//                )
//            }
//        }
//    }
//}
//
//@Composable
//fun SocialMediaIcons(
//    iconRes: DrawableResource,
//    contentDescription: String,
//    onClick: () -> Unit,
//) = IconButton(onClick) {
//    Icon(
//        painter = painterResource(iconRes),
//        contentDescription = contentDescription,
//        tint = AppColors.PureWhite
//    )
//}
//
//data class SocialMedia(
//    val name: String,
//    val iconRes: DrawableResource,
//    val url: String,
//)
//
//val availableSocialMediaPlatforms = listOf(
//    SocialMedia(
//        name = "Facebook",
//        iconRes = Res.drawable.ic_facebook,
//        url = "https://www.facebook.com"
//    ),
//    SocialMedia(
//        name = "Twitter",
//        iconRes = Res.drawable.ic_twitter,
//        url = "https://www.twitter.com"
//    ),
//    SocialMedia(
//        name = "Instagram",
//        iconRes = Res.drawable.ic_instagram,
//        url = "https://www.instagram.com"
//    )
//)
