package org.cufy.mmrpc.editor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import io.github.vinceglb.filekit.FileKit
import io.github.vinceglb.filekit.dialogs.init
import org.cufy.mmrpc.editor.MainApplication.Companion.globalLocal
import org.cufy.mmrpc.editor.MainApplication.Companion.globalNavCtrl
import org.cufy.mmrpc.editor.components.window.main.MainWindow
import org.cufy.mmrpc.editor.components.wrapper.AndroidUniWindowCompat
import org.cufy.mmrpc.editor.components.wrapper.UniTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FileKit.init(this)

        val local = globalLocal
        val navCtrl = globalNavCtrl

        setContent {
            context(local, navCtrl) {
                AndroidUniWindowCompat {
                    UniTheme {
                        MainWindow()
                    }
                }
            }
        }
    }
}
