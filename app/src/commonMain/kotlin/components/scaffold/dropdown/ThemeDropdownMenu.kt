package org.cufy.mmrpc.editor.components.scaffold.dropdown

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.ZoomIn
import androidx.compose.material.icons.filled.ZoomOut
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch
import org.cufy.mmrpc.editor.*
import org.cufy.mmrpc.editor.common.preferences.flowUiColorsInOrDefault
import org.cufy.mmrpc.editor.common.preferences.flowUiScaleInOrDefault
import org.cufy.mmrpc.editor.common.preferences.updateUiColors
import org.cufy.mmrpc.editor.common.preferences.updateUiScale

@Composable
fun ThemeDropdownMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    clientLocal: ClientLocal,
    modifier: Modifier = Modifier,
) {
    val coroutineScope = rememberCoroutineScope()

    val currentUiScale by clientLocal
        .flowUiScaleInOrDefault(coroutineScope)
        .collectAsState()
    val currentUiColors by clientLocal
        .flowUiColorsInOrDefault(coroutineScope)
        .collectAsState()

    fun onChangeUiColors(colors: String) =
        coroutineScope.launch {
            clientLocal.updateUiColors(colors)
            onDismissRequest()
        }

    fun onChangeUiScale(delta: Int) =
        coroutineScope.launch {
            clientLocal.updateUiScale { it + delta }
        }

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        content = {
            DropdownMenuItem(
                text = { Text("Light") },
                leadingIcon = { Icon(Icons.Default.LightMode, null) },
                enabled = currentUiColors != UI_COLORS_LIGHT,
                onClick = { onChangeUiColors(UI_COLORS_LIGHT) },
            )
            DropdownMenuItem(
                text = { Text("Dark") },
                leadingIcon = { Icon(Icons.Default.DarkMode, null) },
                enabled = currentUiColors != UI_COLORS_DARK,
                onClick = { onChangeUiColors(UI_COLORS_DARK) },
            )
            DropdownMenuItem(
                text = { Text("%$currentUiScale") },
                leadingIcon = {
                    IconButton(
                        content = { Icon(Icons.Default.ZoomOut, "Zoom Out") },
                        enabled = currentUiScale > UI_SCALE_MIN,
                        onClick = { onChangeUiScale(delta = -10) },
                    )
                },
                trailingIcon = {
                    IconButton(
                        content = { Icon(Icons.Default.ZoomIn, "Zoom In") },
                        enabled = currentUiScale < UI_SCALE_MAX,
                        onClick = { onChangeUiScale(delta = +10) },
                    )
                },
                onClick = {},
            )
        }
    )
}
