package org.cufy.mmrpc.editor

import androidx.compose.material3.SnackbarHostState
import kotlinx.coroutines.CoroutineScope
import kotlinx.datetime.TimeZone
import org.cufy.mmrpc.editor.support.Repo
import kotlin.random.Random
import kotlin.time.Clock

class Local {
    // etc

    lateinit var clock: Clock
    lateinit var timeZone: TimeZone
    lateinit var random: Random
    lateinit var ioScope: CoroutineScope

    lateinit var snackbar: SnackbarHostState

    // support

    val repo = Repo()
}
