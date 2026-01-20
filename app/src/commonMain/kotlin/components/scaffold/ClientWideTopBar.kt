package org.cufy.mmrpc.editor.components.scaffold

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FileOpen
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.cufy.mmrpc.editor.Local
import org.cufy.mmrpc.editor.common.openMmrpcSpec
import org.cufy.mmrpc.editor.components.scaffold.dropdown.ExportDropdownMenu
import org.cufy.mmrpc.editor.components.scaffold.dropdown.ThemeDropdownMenu

@OptIn(ExperimentalMaterial3Api::class)
@Composable
context(local: Local)
fun ClientWideTopBar(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
) {
    val coroutineScope = rememberCoroutineScope()
    var isThemeMenuOpen by remember { mutableStateOf(false) }
    var isExportMenuOpen by remember { mutableStateOf(false) }

    fun onOpenSchemaClick() = coroutineScope.launch { openMmrpcSpec() }

    CenterAlignedTopAppBar(
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        title = { },
        actions = {
            IconButton({ isThemeMenuOpen = true }) {
                Icon(Icons.Default.Palette, "Theme")

                ThemeDropdownMenu(
                    expanded = isThemeMenuOpen,
                    onDismissRequest = { isThemeMenuOpen = false },
                )
            }
            IconButton({ isExportMenuOpen = true }) {
                Icon(Icons.Default.Save, "Export")

                ExportDropdownMenu(
                    expanded = isExportMenuOpen,
                    onDismissRequest = { isExportMenuOpen = false },
                )
            }

            IconButton(::onOpenSchemaClick) {
                Icon(Icons.Default.FileOpen, "Open Schema")
            }

            Spacer(Modifier.width(12.dp))
        }
    )
}
