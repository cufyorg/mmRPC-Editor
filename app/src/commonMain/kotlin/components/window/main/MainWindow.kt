package org.cufy.mmrpc.editor.components.window.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import net.lsafer.compose.simplenav.NavHost
import org.cufy.mmrpc.editor.Local
import org.cufy.mmrpc.editor.MainNavController
import org.cufy.mmrpc.editor.MainRoute
import org.cufy.mmrpc.editor.components.page.empty.EmptyPage
import org.cufy.mmrpc.editor.components.page.home.HomePage
import org.cufy.mmrpc.editor.components.page.namespace.NamespacePage
import org.cufy.mmrpc.editor.components.page.protocol.ProtocolPage

@Composable
context(local: Local, navCtrl: MainNavController)
fun MainWindow(modifier: Modifier = Modifier) {
    if (local.repo.displaySpec.isEmpty) {
        EmptyPage()
        return
    }

    NavHost(navCtrl) {
        entryScope<MainRoute.Home> { HomePage(modifier) }
        entryScope<MainRoute.Protocol> { ProtocolPage(modifier) }
        entryScope<MainRoute.Namespace> { NamespacePage(modifier) }
    }
}
