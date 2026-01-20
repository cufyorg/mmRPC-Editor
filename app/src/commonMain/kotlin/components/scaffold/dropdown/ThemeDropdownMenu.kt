package org.cufy.mmrpc.editor.components.scaffold.dropdown

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.ZoomIn
import androidx.compose.material.icons.filled.ZoomOut
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.cufy.mmrpc.editor.Local
import org.cufy.mmrpc.editor.UI_COLORS_DARK
import org.cufy.mmrpc.editor.UI_COLORS_LIGHT
import org.cufy.mmrpc.editor.support.Repo

@Composable
context(local: Local)
fun ThemeDropdownMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        content = {
            DropdownMenuItem(
                text = { Text("Light") },
                leadingIcon = { Icon(Icons.Default.LightMode, null) },
                enabled = local.repo.uiColors != UI_COLORS_LIGHT,
                onClick = { local.repo.uiColors = UI_COLORS_LIGHT },
            )
            DropdownMenuItem(
                text = { Text("Dark") },
                leadingIcon = { Icon(Icons.Default.DarkMode, null) },
                enabled = local.repo.uiColors != UI_COLORS_DARK,
                onClick = { local.repo.uiColors = UI_COLORS_DARK },
            )
            DropdownMenuItem(
                text = { Text("%${local.repo.uiScale}") },
                leadingIcon = {
                    IconButton(
                        content = { Icon(Icons.Default.ZoomOut, "Zoom Out") },
                        enabled = local.repo.uiScale > Repo.UI_SCALE_MIN,
                        onClick = { local.repo.uiScale -= 10 },
                    )
                },
                trailingIcon = {
                    IconButton(
                        content = { Icon(Icons.Default.ZoomIn, "Zoom In") },
                        enabled = local.repo.uiScale < Repo.UI_SCALE_MAX,
                        onClick = { local.repo.uiScale += 10 },
                    )
                },
                onClick = {},
            )
        }
    )
}
