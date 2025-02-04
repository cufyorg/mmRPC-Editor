package org.cufy.mmrpc.editor

import androidx.compose.material3.SnackbarHostState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.serialization.json.JsonObject
import net.lsafer.sundry.compose.simplenav.SimpleNavController
import net.lsafer.sundry.storage.SimpleDataStore
import java.nio.file.Path
import kotlin.random.Random

class ClientLocal {
    lateinit var dataDir: Path
    lateinit var cacheDir: Path

    // etc

    lateinit var clock: Clock
    lateinit var random: Random
    lateinit var timeZone: TimeZone
    lateinit var ioScope: CoroutineScope

    lateinit var dataStore: SimpleDataStore<JsonObject>
    lateinit var navController: SimpleNavController<ClientRoute>
    lateinit var specState: StateFlow<ClientSpec>
    lateinit var snackbar: SnackbarHostState
}
