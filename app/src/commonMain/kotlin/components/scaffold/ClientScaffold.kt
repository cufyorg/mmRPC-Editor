package org.cufy.mmrpc.editor.components.scaffold

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import org.cufy.mmrpc.editor.Local
import org.cufy.mmrpc.editor.MainNavController
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
context(local: Local, navCtrl: MainNavController)
fun ClientScaffold(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit,
) {
    val spec = local.repo.displaySpec
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    PermanentNavigationDrawer(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .then(modifier),
        drawerContent = {
            ClientNavigationDrawer()
        },
        content = {
            Scaffold(
                modifier = Modifier.fillMaxWidth(),
                topBar = {
                    ClientWideTopBar(
                        modifier = Modifier.fillMaxWidth(),
                        scrollBehavior = scrollBehavior,
                    )
                },
                snackbarHost = {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.BottomEnd,
                        content = { SnackbarHost(local.snackbar) }
                    )
                },
                floatingActionButtonPosition = FabPosition.End,
                content = { paddingValues ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        contentAlignment = Alignment.TopCenter,
                        content = {
                            content()

                            Column(Modifier.align(Alignment.BottomEnd)) {
                                Text(
                                    fontSize = 15.sp,
                                    color = MaterialTheme.colorScheme.tertiary,
                                    text = "loaded: ${spec.name} (${spec.version})",
                                )
                                Text(
                                    fontSize = 15.sp,
                                    color = MaterialTheme.colorScheme.tertiary,
                                    text = "pwd: ${File(".").absolutePath}"
                                )
                            }
                        },
                    )
                },
            )
        }
    )
}
