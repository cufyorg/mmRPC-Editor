package org.cufy.mmrpc.editor

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.window.application
import org.cufy.mmrpc.editor.common.preferences.flowUiColorsInOrDefault
import org.cufy.mmrpc.editor.common.preferences.flowUiScaleInOrDefault
import org.cufy.mmrpc.editor.components.window.main.MainWindow
import org.cufy.mmrpc.editor.components.wrapper.ClientTheme
import org.cufy.mmrpc.editor.components.wrapper.DesktopClientWindowCompat

suspend fun main() {
    val clientLocal = createDesktopClientLocal()

    application {
        val coroutineScope = rememberCoroutineScope()
        val application = this@application

        val uiColors by clientLocal
            .flowUiColorsInOrDefault(coroutineScope)
            .collectAsState()
        val uiScale by clientLocal
            .flowUiScaleInOrDefault(coroutineScope)
            .collectAsState()

        DesktopClientWindowCompat(application, clientLocal) {
            ClientTheme(uiScale, uiColors) {
                MainWindow(clientLocal)
            }
        }
    }
}
