package org.cufy.mmrpc.editor.scripts

import co.touchlab.kermit.Logger
import co.touchlab.kermit.platformLogWriter
import org.cufy.mmrpc.editor.ClientLocal
import org.cufy.mmrpc.editor.util.SimpleLogFormatter

@Suppress("FunctionName")
fun ClientLocal.init_kermit_writers() {
    Logger.setLogWriters(platformLogWriter(SimpleLogFormatter(clock)))
}
