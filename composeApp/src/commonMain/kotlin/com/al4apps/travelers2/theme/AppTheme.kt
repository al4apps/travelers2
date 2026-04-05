package ru.intabia.platform.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import ru.expasoft.digitalpictureframe.theme.LAppColors
import ru.expasoft.digitalpictureframe.theme.LocalDPFColors
import ru.expasoft.digitalpictureframe.theme.lAppColors

object AppTheme {

    val typography: AppTypography
        @Composable
        get() = LocalTypography().current

    val colors: LAppColors
        @Composable
        @ReadOnlyComposable
        get() = LocalDPFColors.current
}

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    val typography = lappTypography()
    val local = LocalTypography()
    CompositionLocalProvider(
        local provides typography,
        LocalDPFColors provides lAppColors,
        content = content
    )
}
