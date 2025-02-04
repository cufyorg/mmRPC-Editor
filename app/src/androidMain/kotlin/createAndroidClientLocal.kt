package org.cufy.mmrpc.editor

import android.app.Application
import androidx.compose.material3.SnackbarHostState
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.serialization.serializer
import net.lsafer.sundry.compose.simplenav.InMemorySimpleNavController
import net.lsafer.sundry.storage.createFileJsonObjectDataStore
import org.cufy.mmrpc.editor.scripts.createIOCoroutineScope
import org.cufy.mmrpc.editor.scripts.createSpecState
import org.cufy.mmrpc.editor.scripts.init_kermit_writers
import org.cufy.mmrpc.editor.scripts.register_shutdown_hook
import kotlin.random.Random

suspend fun createAndroidClientLocal(
    application: Application,
): ClientLocal {
    val clientLocal = ClientLocal()
    clientLocal.dataDir = application.filesDir.toPath()
    clientLocal.cacheDir = application.cacheDir.toPath()

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
        serializer(), // <- complains about inlining not working with different JVM version
    )
    clientLocal.specState = createSpecState(clientLocal)
    clientLocal.snackbar = SnackbarHostState()
    clientLocal.init_kermit_writers()
    clientLocal.register_shutdown_hook()

    return clientLocal
}
