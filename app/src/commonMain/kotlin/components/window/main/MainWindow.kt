package org.cufy.mmrpc.editor.components.window.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import net.lsafer.sundry.compose.simplenav.current
import org.cufy.mmrpc.editor.ClientLocal
import org.cufy.mmrpc.editor.HomePage
import org.cufy.mmrpc.editor.NamespacePage
import org.cufy.mmrpc.editor.ProtocolPage
import org.cufy.mmrpc.editor.components.page.empty.EmptyPage
import org.cufy.mmrpc.editor.components.page.home.HomePage
import org.cufy.mmrpc.editor.components.page.namespace.NamespacePage
import org.cufy.mmrpc.editor.components.page.protocol.ProtocolPage

@Composable
fun MainWindow(
    clientLocal: ClientLocal,
) {
    val spec by clientLocal.specState.collectAsState()

    if (spec.isEmpty) {
        EmptyPage(clientLocal)
        return
    }

    when (val currentRoute = clientLocal.navController.current) {
        is HomePage -> HomePage(currentRoute, clientLocal)
        is NamespacePage -> NamespacePage(currentRoute, clientLocal)
        is ProtocolPage -> ProtocolPage(currentRoute, clientLocal)

        else -> {}
    }
}
