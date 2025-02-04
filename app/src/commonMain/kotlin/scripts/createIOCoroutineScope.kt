package org.cufy.mmrpc.editor.scripts

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import net.lsafer.sundry.compose.util.platformIODispatcher
import org.cufy.mmrpc.editor.moduleLogger

fun createIOCoroutineScope(): CoroutineScope {
    val handler = CoroutineExceptionHandler { _, e ->
        moduleLogger.e(e.message.orEmpty(), e)
    }

    return CoroutineScope(handler + platformIODispatcher + SupervisorJob())
}
