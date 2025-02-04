package org.cufy.mmrpc.editor.components.wrapper

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import org.cufy.mmrpc.editor.*

@Composable
fun ClientTheme(
    uiScale: Int,
    uiColors: String,
    content: @Composable () -> Unit,
) {
    val density = Density(
        density = (uiScale + UI_SCALE_OFFSET) * UI_SCALE_FACTOR,
        fontScale = (uiScale + UI_SCALE_OFFSET) * UI_SCALE_FACTOR
                * UI_SCALE_FONT_FACTOR,
    )

    CompositionLocalProvider(LocalDensity provides density) {
        MaterialTheme(
            colorScheme = when (uiColors) {
                UI_COLORS_DARK -> darkColorScheme()
                UI_COLORS_LIGHT -> lightColorScheme()
                else -> lightColorScheme()
            },
            shapes = Shapes(
                extraSmall = RoundedCornerShape(15.dp),
                small = RoundedCornerShape(20.dp),
                medium = RoundedCornerShape(25.dp),
                large = RoundedCornerShape(30.dp),
                extraLarge = RoundedCornerShape(35.dp),
            ),
            content = content,
        )
    }
}
