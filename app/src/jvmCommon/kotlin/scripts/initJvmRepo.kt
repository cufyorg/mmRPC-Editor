package org.cufy.mmrpc.editor.scripts

import androidx.compose.runtime.snapshotFlow
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.cufy.json.*
import org.cufy.mmrpc.MmrpcSpec
import org.cufy.mmrpc.editor.Local
import org.cufy.mmrpc.experimental.fromYamlString
import org.cufy.mmrpc.experimental.toYamlString
import java.io.File
import kotlin.time.Duration.Companion.seconds

@OptIn(FlowPreview::class)
context(local: Local)
fun initJvmRepo(dataDir: File) {
    val file = dataDir.resolve("datastore.json")

    local.repo.data += file.readJson()
    snapshotFlow { local.repo.data.toMap() }
        .debounce(1.seconds)
        .onEach { file.writeJson(it) }
        .launchIn(local.ioScope)

    val fileSpec = dataDir.resolve("spec.mmrpc.yaml")
    local.repo.spec = fileSpec.readSpec()
    snapshotFlow { local.repo.spec }
        .debounce(1.seconds)
        .onEach { fileSpec.writeSpec(it) }
        .launchIn(local.ioScope)

}

private fun File.readJson(): JsonObjectLike {
    return try {
        readText().decodeJsonOrNull()?.asJsonObjectOrNull ?: JsonObject()
    } catch (e: Exception) {
        e.printStackTrace()
        return JsonObject()
    }
}

private fun File.writeJson(value: JsonObjectLike) {
    try {
        parentFile?.mkdir()
        writeText(value.serializeToJsonString())
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

private fun File.readSpec(): MmrpcSpec? {
    return try {
        MmrpcSpec.fromYamlString(readText())
    } catch (e: Throwable) {
        e.printStackTrace()
        return null
    }
}

private fun File.writeSpec(value: MmrpcSpec?) {
    try {
        if (value == null) {
            delete()
        } else {
            parentFile?.mkdir()
            writeText(value.toYamlString())
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
