package org.cufy.mmrpc.editor

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainApplication : Application() {
    companion object {
        val globalClientLocal = MutableStateFlow<ClientLocal?>(null)
    }

    override fun onCreate() {
        super.onCreate()

        CoroutineScope(Dispatchers.IO).launch {
            val application = this@MainApplication
            val clientLocal = createAndroidClientLocal(
                application = application,
            )

            globalClientLocal.emit(clientLocal)
        }
    }
}
