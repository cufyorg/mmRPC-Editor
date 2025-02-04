package org.cufy.mmrpc.editor.util

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance

@Composable
fun dynamicColor(light: Color, dark: Color): Color {
    val l = MaterialTheme.colorScheme.background.luminance()
    return if (l > .5f) light else dark
}
