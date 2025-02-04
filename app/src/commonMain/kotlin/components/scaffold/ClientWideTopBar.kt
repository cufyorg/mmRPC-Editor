package org.cufy.mmrpc.editor.components.scaffold

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FileOpen
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.cufy.mmrpc.editor.ClientLocal
import org.cufy.mmrpc.editor.common.openMmrpcSpec
import org.cufy.mmrpc.editor.components.scaffold.dropdown.ThemeDropdownMenu

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientWideTopBar(
    modifier: Modifier = Modifier,
    clientLocal: ClientLocal,
    scrollBehavior: TopAppBarScrollBehavior? = null,
) {
    val coroutineScope = rememberCoroutineScope()
    var isThemeMenuOpen by remember { mutableStateOf(false) }

    fun onOpenSchemaClick() = coroutineScope.launch { openMmrpcSpec(clientLocal) }

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
                    clientLocal = clientLocal,
                )
            }

            IconButton(::onOpenSchemaClick) {
                Icon(Icons.Default.FileOpen, "Open Schema")
            }

            Spacer(Modifier.width(12.dp))
        }
    )
}
