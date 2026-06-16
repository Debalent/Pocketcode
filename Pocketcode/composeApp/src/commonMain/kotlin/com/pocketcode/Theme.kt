package com.pocketcode

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Brand palette
val ElectricOrange = Color(0xFFFF6B00)
val DeepCharcoal = Color(0xFF1A1A1A)
val NeonCyan = Color(0xFF00C4FF)
val RichBlack = Color(0xFF0D0D0D)
val SuccessGreen = Color(0xFF00E676)
val WarningYellow = Color(0xFFFFEA00)
val ErrorRed = Color(0xFFFF1744)

// Supporting tones for cards, borders, and elevated surfaces
val Graphite = Color(0xFF242424)
val Slate = Color(0xFF2E2E2E)
val Fog = Color(0xFFECECEC)

private val DarkColorScheme = darkColorScheme(
    primary = ElectricOrange,
    onPrimary = Color.White,
    primaryContainer = Color(0xFF3D1F00),
    onPrimaryContainer = Color(0xFFFFD0AF),
    secondary = DeepCharcoal,
    onSecondary = Color.White,
    secondaryContainer = Graphite,
    onSecondaryContainer = Fog,
    tertiary = NeonCyan,
    onTertiary = Color.Black,
    tertiaryContainer = Color(0xFF003344),
    onTertiaryContainer = Color(0xFFAEEBFF),
    background = RichBlack,
    onBackground = Color.White,
    surface = DeepCharcoal,
    onSurface = Color.White,
    surfaceVariant = Slate,
    onSurfaceVariant = Color(0xFFD7D7D7),
    outline = Color(0xFF5A5A5A),
    error = ErrorRed,
    onError = Color.White,
    errorContainer = Color(0xFF680015),
    onErrorContainer = Color(0xFFFFD9DF)
)

private val LightColorScheme = lightColorScheme(
    primary = ElectricOrange,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFFFD8BF),
    onPrimaryContainer = Color(0xFF2A1200),
    secondary = Color(0xFF353535),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFE9E9E9),
    onSecondaryContainer = Color(0xFF111111),
    tertiary = NeonCyan,
    onTertiary = Color.Black,
    tertiaryContainer = Color(0xFFCDEFFF),
    onTertiaryContainer = Color(0xFF002733),
    background = Color(0xFFF8F8F8),
    onBackground = RichBlack,
    surface = Color(0xFFFFFFFF),
    onSurface = RichBlack,
    surfaceVariant = Color(0xFFEDEDED),
    onSurfaceVariant = Color(0xFF4A4A4A),
    outline = Color(0xFF8A8A8A),
    error = ErrorRed,
    onError = Color.White,
    errorContainer = Color(0xFFFFD9E0),
    onErrorContainer = Color(0xFF390009)
)

val PocketCodeTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 34.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    ),
    displayMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        lineHeight = 34.sp,
        letterSpacing = 0.sp
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    labelLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 13.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp
    ),
    labelMedium = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.6.sp
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.3.sp
    )
)

@Composable
fun PocketCodeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = PocketCodeTypography,
        content = content
    )
}
