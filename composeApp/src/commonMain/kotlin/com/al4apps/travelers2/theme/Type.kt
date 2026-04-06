package com.al4apps.travelers2.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
private fun fontsFamily(): FontFamily = FontFamily.SansSerif

@Immutable
data class AppTypography(
    val h1: TextStyle,
    val h2: TextStyle,
    val subtitle: TextStyle,
    val body1: TextStyle,
    val body2: TextStyle,
    val caption: TextStyle,
)

@Composable
fun lappTypography(): AppTypography {
    val fonts = fontsFamily()
    return AppTypography(
        h1 = TextStyle(
            fontFamily = fonts,
            fontSize = 54.sp,
            lineHeight = 64.sp
        ).semibold,
        h2 = TextStyle(
            fontFamily = fonts,
            fontSize = 24.sp,
            lineHeight = 38.sp,
        ).medium,
        subtitle = TextStyle(
            fontFamily = fonts,
            fontSize = 16.sp,
            lineHeight = 20.sp
        ).medium,
        body1 = TextStyle(
            fontFamily = fonts,
            fontSize = 16.sp,
            lineHeight = 18.sp
        ).regular,
        body2 = TextStyle(
            fontFamily = fonts,
            fontSize = 14.sp,
            lineHeight = 16.sp
        ).regular,
        caption = TextStyle(
            fontFamily = fonts,
            fontSize = 12.sp,
            lineHeight = 14.sp
        ).regular
    )
}

val TextStyle.bold: TextStyle
    get() = copy(fontWeight = FontWeight(700))

val TextStyle.semibold: TextStyle
    get() = copy(fontWeight = FontWeight(600))

val TextStyle.medium: TextStyle
    get() = copy(fontWeight = FontWeight(500))

val TextStyle.regular: TextStyle
    get() = copy(fontWeight = FontWeight(400))

@Composable
fun LocalTypography(): ProvidableCompositionLocal<AppTypography> {
    val typography = lappTypography()
    return staticCompositionLocalOf {
        typography
    }
}
