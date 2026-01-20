package org.cufy.mmrpc.editor.scripts

import kotlinx.coroutines.cancel
import kotlinx.coroutines.runBlocking
import org.cufy.mmrpc.editor.Local

context(local: Local)
fun registerJvmShutdownHook() {
    Runtime.getRuntime().addShutdownHook(Thread {
        runBlocking {
            local.ioScope.cancel()
        }
    })
}
