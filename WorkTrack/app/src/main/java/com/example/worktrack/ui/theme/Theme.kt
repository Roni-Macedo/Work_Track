package com.example.worktrack.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(

    primary = BluePrimary,
    onPrimary = SurfaceLight,

    secondary = GreenSecondary,
    onSecondary = SurfaceLight,

    tertiary = PurpleAccent,

    background = BackgroundLight,
    onBackground = TextPrimary,

    surface = SurfaceLight,
    onSurface = TextPrimary,

    error = RedError
)

private val DarkColors = darkColorScheme(

    primary = BluePrimary,

    secondary = GreenSecondary,

    tertiary = PurpleAccent,

    background = BackgroundDark,
    onBackground = SurfaceLight,

    surface = SurfaceDark,
    onSurface = SurfaceLight,

    error = RedError
)

@Composable
fun WorkTrackTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = Typography(),
        content = content
    )
}
