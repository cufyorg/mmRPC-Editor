package org.cufy.mmrpc.editor

import android.app.Application
import androidx.compose.material3.SnackbarHostState
import kotlinx.coroutines.*
import kotlinx.datetime.TimeZone
import org.cufy.mmrpc.editor.scripts.initAndroidLogFacade
import org.cufy.mmrpc.editor.scripts.initJvmRepo
import org.cufy.mmrpc.editor.scripts.registerJvmShutdownHook
import kotlin.random.Random
import kotlin.time.Clock

context(app: Application)
fun createAndroidLocal(): Local {
    val local = Local()
    local.clock = Clock.System
    local.timeZone = TimeZone.currentSystemDefault()
    local.random = Random.Default
    local.ioScope = CoroutineScope(
        CoroutineName("LocalIoScope") + Dispatchers.IO + SupervisorJob() +
                CoroutineExceptionHandler { _, e ->
                    moduleLogger.e("Unhandled coroutine exception", e)
                }
    )
    local.snackbar = SnackbarHostState()
    context(local) {
        registerJvmShutdownHook()
        initAndroidLogFacade()
        initJvmRepo(app.dataDir)
    }
    return local
}
