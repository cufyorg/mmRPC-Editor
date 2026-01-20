package org.cufy.mmrpc.editor.scripts

import co.touchlab.kermit.Logger
import co.touchlab.kermit.SystemWriter
import org.cufy.mmrpc.editor.Local
import org.cufy.mmrpc.editor.util.SimpleLogFormatter

context(local: Local)
fun initDesktopLogFacade() {
    val formatter = SimpleLogFormatter(local.timeZone, local.clock)
    Logger.setLogWriters(SystemWriter(formatter))
}
