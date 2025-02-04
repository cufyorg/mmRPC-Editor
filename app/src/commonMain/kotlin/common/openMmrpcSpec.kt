package org.cufy.mmrpc.editor.common

import com.charleskorn.kaml.Yaml
import io.github.vinceglb.filekit.core.FileKit
import io.github.vinceglb.filekit.core.extension
import io.github.vinceglb.filekit.core.pickFile
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.cufy.mmrpc.MmrpcSpec
import org.cufy.mmrpc.editor.ClientLocal
import org.cufy.mmrpc.editor.common.preferences.updateDataSpec
import org.cufy.mmrpc.editor.moduleLogger
import java.io.IOException

suspend fun openMmrpcSpec(clientLocal: ClientLocal) {
    val file = FileKit.pickFile(
        title = "Select mmrpc schema",
    )

    file ?: return

    val source = try {
        file.readBytes().decodeToString()
    } catch (cause: IOException) {
        moduleLogger.e("Couldn't read file: ${file.path}", cause)
        clientLocal.snackbar.showSnackbar("Couldn't read file: ${file.path}")
        return
    }

    when (file.extension) {
        "json" -> try {
            val mmrpcSpec = Json.decodeFromString<MmrpcSpec>(source)

            clientLocal.updateDataSpec(mmrpcSpec)
        } catch (cause: Exception) {
            moduleLogger.e("Couldn't decode file: ${file.path}", cause)
            clientLocal.snackbar.showSnackbar("Couldn't decode file: ${file.path}")
        }

        "yaml", "yml" -> try {
            val mmrpcSpec = Yaml.default.decodeFromString<MmrpcSpec>(source)

            clientLocal.updateDataSpec(mmrpcSpec)
        } catch (cause: Exception) {
            moduleLogger.e("Couldn't decode file: ${file.path}", cause)
            clientLocal.snackbar.showSnackbar("Couldn't decode file: ${file.path}")
        }

        else -> {
            moduleLogger.e("Unrecognized file extension: ${file.path}")
            clientLocal.snackbar.showSnackbar("Unrecognized file extension: ${file.path}")
        }
    }
}
