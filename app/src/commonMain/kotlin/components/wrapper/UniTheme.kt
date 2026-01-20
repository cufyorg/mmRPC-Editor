package org.cufy.mmrpc.editor.components.wrapper

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import org.cufy.mmrpc.editor.Local
import org.cufy.mmrpc.editor.UI_COLORS_DARK
import org.cufy.mmrpc.editor.UI_COLORS_LIGHT

private const val CustomScaleOffset = 80f
private const val CustomScaleFactor = .01f
private const val CustomScaleFontFactor = .6f

private val CustomDarkColorScheme = darkColorScheme(
    outline = Color(red = 145, green = 145, blue = 147, alpha = 80),
)
private val CustomLightColorScheme = lightColorScheme(
    outline = Color(red = 38, green = 38, blue = 40, alpha = 60),
)

@Composable
context(local: Local)
fun UniTheme(content: @Composable () -> Unit) {
    val uiColors = local.repo.uiColors
    val uiScale = local.repo.uiScale

    val density = Density(
        density = (uiScale + CustomScaleOffset) * CustomScaleFactor,
        fontScale = (uiScale + CustomScaleOffset) * CustomScaleFactor * CustomScaleFontFactor,
    )

    CompositionLocalProvider(LocalDensity provides density) {
        MaterialTheme(
            colorScheme = when {
                uiColors == UI_COLORS_DARK -> CustomDarkColorScheme
                uiColors == UI_COLORS_LIGHT -> CustomLightColorScheme
                isSystemInDarkTheme() -> CustomDarkColorScheme
                else -> CustomLightColorScheme
            },
            shapes = Shapes(
                extraSmall = RoundedCornerShape(10.dp),
                small = RoundedCornerShape(12.5.dp),
                medium = RoundedCornerShape(15.dp),
                large = RoundedCornerShape(17.5.dp),
                extraLarge = RoundedCornerShape(20.dp),
            ),
            content = content,
        )
    }
}
