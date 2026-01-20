package org.cufy.mmrpc.editor

import androidx.compose.material3.SnackbarHostState
import kotlinx.coroutines.*
import kotlinx.datetime.TimeZone
import org.cufy.mmrpc.editor.scripts.getStdDataDir
import org.cufy.mmrpc.editor.scripts.initDesktopLogFacade
import org.cufy.mmrpc.editor.scripts.initJvmRepo
import org.cufy.mmrpc.editor.scripts.registerJvmShutdownHook
import kotlin.random.Random
import kotlin.time.Clock

fun createDesktopLocal(): Local {
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
        initDesktopLogFacade()
        initJvmRepo(getStdDataDir())
    }
    return local
}
