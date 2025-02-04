package org.cufy.mmrpc.editor.scripts

import kotlinx.coroutines.cancel
import kotlinx.coroutines.runBlocking
import org.cufy.mmrpc.editor.ClientLocal

@Suppress("FunctionName")
fun ClientLocal.register_shutdown_hook() {
    Runtime.getRuntime().addShutdownHook(Thread {
        runBlocking {
            ioScope.cancel()
        }
    })
}
