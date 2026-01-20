package org.cufy.mmrpc.editor.scripts

import co.touchlab.kermit.LogcatWriter
import co.touchlab.kermit.Logger
import org.cufy.mmrpc.editor.Local
import org.cufy.mmrpc.editor.util.SimpleLogFormatter

context(local: Local)
fun initAndroidLogFacade() {
    val formatter = SimpleLogFormatter(local.timeZone, local.clock)
    Logger.setLogWriters(LogcatWriter(formatter))
}
