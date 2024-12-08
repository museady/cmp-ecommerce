package com.museady.cmp.ecommerce.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ecommerce_cmp.composeapp.generated.resources.Res
import ecommerce_cmp.composeapp.generated.resources.manrope_bold
import ecommerce_cmp.composeapp.generated.resources.manrope_medium
import ecommerce_cmp.composeapp.generated.resources.manrope_regular
import org.jetbrains.compose.resources.Font

internal val manropeFontFamily
    @Composable get() = FontFamily(
        Font(Res.font.manrope_regular, FontWeight.Normal),
        Font(Res.font.manrope_bold, FontWeight.Bold),
        Font(Res.font.manrope_medium, FontWeight.Medium)
    )

/**
 * Typography used in the e-commerce design system.
 */
val AppTypography
    @Composable get() = Typography(
        //H1 - Manrope Bold - 56px (All Caps, 58px Line Space, 2px Char. Space)
        displayLarge = TextStyle(
            fontFamily = manropeFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 56.sp,
            letterSpacing = 2.sp,
            lineHeight = 58.sp
        ),
        //H2 - Manrope Bold - 40px (All Caps, 44px Line Space, 1,5px Char. Space)
        displayMedium = TextStyle(
            fontFamily = manropeFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp,
            letterSpacing = 1.5.sp,
            lineHeight = 44.sp
        ),
        //H3 - Manrope Bold - 32px (All Caps, 36px Line Space, 1,15px Char. Space)
        displaySmall = TextStyle(
            fontFamily = manropeFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            letterSpacing = 1.15.sp,
            lineHeight = 36.sp
        ),
        //H4 - Manrope Bold - 28px (All Caps, 38px Line Space, 2px Char. Space)
        headlineLarge = TextStyle(
            fontFamily = manropeFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            letterSpacing = 2.sp,
            lineHeight = 38.sp
        ),
        //H5 - Manrope Bold - 24px (All Caps, 33px Line Space, 1,7px Char. Space)
        headlineMedium = TextStyle(
            fontFamily = manropeFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            letterSpacing = 1.15.sp,
            lineHeight = 36.sp
        ),
        //H6 - Manrope Bold - 18px (All Caps, 24px Line Space, 1,3px Char. Space)
        titleLarge = TextStyle(
            fontFamily = manropeFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            letterSpacing = 1.3.sp,
            lineHeight = 24.sp
        ),
        //Overline - Manrope Regular - 14px (All Caps, 19px Line Space, 10px Char. Space)
        titleMedium = TextStyle(
            fontFamily = manropeFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            letterSpacing = 10.sp,
            lineHeight = 19.sp
        ),
        //Sub Title - Manrope Bold - 13px (All Caps, 25px Line Space, 1px Char. Space)
        titleSmall = TextStyle(
            fontFamily = manropeFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp,
            letterSpacing = 1.sp,
            lineHeight = 25.sp
        ),
        //Body - Manrope Medium - 15px (25px Line Space)
        bodyLarge = TextStyle(
            fontFamily = manropeFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            lineHeight = 25.sp,
            letterSpacing = 1.07.sp
        ),
        //Used in text field header title
        bodyMedium = TextStyle(
            fontFamily = manropeFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            lineHeight = 20.sp,
            letterSpacing = (-.21).sp
        ),
        //Used in text field header wrong text
        bodySmall = TextStyle(
            fontFamily = manropeFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            lineHeight = 20.sp,
            letterSpacing = (-.21).sp
        ),//Used in home topAppBar actions
        labelLarge = TextStyle(
            fontFamily = manropeFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp,
            letterSpacing = 2.sp,
            lineHeight = 25.sp
        ),//Used in product desc & copyright text
        labelMedium = TextStyle(
            fontFamily = manropeFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp,
            letterSpacing = 0.sp,
            lineHeight = 25.sp
        ),
        labelSmall = TextStyle(
            fontFamily = manropeFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            letterSpacing = .3.sp,
            lineHeight = 16.sp
        )
    )
