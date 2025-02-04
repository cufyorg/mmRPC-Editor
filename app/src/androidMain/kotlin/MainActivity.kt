package org.cufy.mmrpc.editor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import org.cufy.mmrpc.editor.common.preferences.flowUiColorsInOrDefault
import org.cufy.mmrpc.editor.common.preferences.flowUiScaleInOrDefault
import org.cufy.mmrpc.editor.components.window.main.MainWindow
import org.cufy.mmrpc.editor.components.wrapper.AndroidClientWindowCompat
import org.cufy.mmrpc.editor.components.wrapper.ClientTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val coroutineScope = rememberCoroutineScope()
            val activity = this@MainActivity
            val clientLocal = MainApplication
                .globalClientLocal
                .collectAsState()
                .value ?: return@setContent

            val uiColors by clientLocal
                .flowUiColorsInOrDefault(coroutineScope)
                .collectAsState()
            val uiScale by clientLocal
                .flowUiScaleInOrDefault(coroutineScope)
                .collectAsState()

            AndroidClientWindowCompat(clientLocal, activity) {
                ClientTheme(uiScale, uiColors) {
                    MainWindow(clientLocal)
                }
            }
        }
    }
}
