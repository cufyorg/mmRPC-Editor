package org.cufy.mmrpc.editor

import androidx.compose.material3.SnackbarHostState
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import net.lsafer.sundry.compose.simplenav.InMemorySimpleNavController
import net.lsafer.sundry.storage.createFileJsonObjectDataStore
import okio.Path.Companion.toPath
import org.cufy.mmrpc.editor.scripts.*
import kotlin.random.Random

suspend fun createDesktopClientLocal(): ClientLocal {
    val clientLocal = ClientLocal()
    clientLocal.dataDir = getStdDataDir().toPath()
    clientLocal.cacheDir = getStdCacheDir().toPath()

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
