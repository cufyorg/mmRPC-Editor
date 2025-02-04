package org.cufy.mmrpc.editor.components.scaffold

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import org.cufy.mmrpc.editor.ClientLocal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientScaffold(
    modifier: Modifier = Modifier,
    clientLocal: ClientLocal,

    floatingActionButton: @Composable () -> Unit = {},
    content: @Composable BoxScope.() -> Unit,
) {
    val spec by clientLocal.specState.collectAsState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    PermanentNavigationDrawer(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .then(modifier),
        drawerContent = {
            ClientNavigationDrawer(clientLocal)
        },
        content = {
            Scaffold(
                modifier = Modifier.fillMaxWidth(),
                topBar = {
                    ClientWideTopBar(
                        modifier = Modifier.fillMaxWidth(),
                        clientLocal = clientLocal,
                        scrollBehavior = scrollBehavior,
                    )
                },
                snackbarHost = {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.BottomEnd,
                        content = { SnackbarHost(clientLocal.snackbar) }
                    )
                },
                floatingActionButton = floatingActionButton,
                floatingActionButtonPosition = FabPosition.End,
                content = { paddingValues ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        contentAlignment = Alignment.TopCenter,
                        content = {
                            content()

                            Text(
                                modifier = Modifier.align(Alignment.BottomEnd),
                                fontSize = 15.sp,
                                color = MaterialTheme.colorScheme.tertiary,
                                text = "Loaded: ${spec.name} (${spec.version})",
                            )
                        },
                    )
                },
            )
        }
    )
}
