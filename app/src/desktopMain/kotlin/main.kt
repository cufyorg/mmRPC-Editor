package org.cufy.mmrpc.editor

import androidx.compose.ui.window.application
import io.github.vinceglb.filekit.FileKit
import net.lsafer.compose.simplenav.InMemoryNavController
import org.cufy.mmrpc.editor.components.window.main.MainWindow
import org.cufy.mmrpc.editor.components.wrapper.DesktopUniWindowCompat
import org.cufy.mmrpc.editor.components.wrapper.UniTheme

fun main() {
    FileKit.init("org.cufy.mmrpc.editor")

    val local = createDesktopLocal()
    val navCtrl = InMemoryNavController<MainRoute>(
        default = MainRoute.Home,
    )

    application {
        context(local, navCtrl) {
            DesktopUniWindowCompat {
                UniTheme {
                    MainWindow()
                }
            }
        }
    }
}
