package org.cufy.mmrpc.editor

import androidx.compose.material3.SnackbarHostState
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import net.lsafer.sundry.compose.simplenav.InMemorySimpleNavController
import net.lsafer.sundry.storage.createFileJsonObjectDataStore
import org.cufy.mmrpc.editor.scripts.createIOCoroutineScope
import org.cufy.mmrpc.editor.scripts.createSpecState
import org.cufy.mmrpc.editor.scripts.init_kermit_writers
import org.cufy.mmrpc.editor.scripts.register_shutdown_hook
import java.io.File
import kotlin.random.Random

suspend fun createDesktopClientLocal(): ClientLocal {
    val clientLocal = ClientLocal()
    clientLocal.dataDir = File("./data").absoluteFile.toPath()
    clientLocal.cacheDir = File("./cache").absoluteFile.toPath()

    clientLocal.clock = Clock.System
    clientLocal.timeZone = TimeZone.currentSystemDefault()
    clientLocal.random = Random.Default
    clientLocal.ioScope = createIOCoroutineScope()

    clientLocal.dataStore = createFileJsonObjectDataStore(
        file = clientLocal.dataDir.resolve("datastore.json").toFile(),
        coroutineScope = clientLocal.ioScope,
    )
    clientLocal.navController = InMemorySimpleNavController(
        InMemorySimpleNavController.State(
            entries = listOf(HomePage),
        ),
    )
    clientLocal.specState = createSpecState(clientLocal)
    clientLocal.snackbar = SnackbarHostState()
    clientLocal.init_kermit_writers()
    clientLocal.register_shutdown_hook()

    return clientLocal
}
