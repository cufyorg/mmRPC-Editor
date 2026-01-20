package org.cufy.mmrpc.editor.common

import io.github.vinceglb.filekit.FileKit
import io.github.vinceglb.filekit.dialogs.openFilePicker
import io.github.vinceglb.filekit.extension
import io.github.vinceglb.filekit.path
import io.github.vinceglb.filekit.readString
import org.cufy.mmrpc.MmrpcSpec
import org.cufy.mmrpc.editor.Local
import org.cufy.mmrpc.editor.moduleLogger
import org.cufy.mmrpc.experimental.fromJsonString
import org.cufy.mmrpc.experimental.fromYamlString
import java.io.IOException

context(local: Local)
suspend fun openMmrpcSpec() {
    val file = FileKit.openFilePicker(
        title = "Select mmrpc schema",
    )

    file ?: return

    val source = try {
        file.readString()
    } catch (cause: IOException) {
        moduleLogger.e("Couldn't read file: ${file.path}", cause)
        local.snackbar.showSnackbar("Couldn't read file: ${file.path}")
        return
    }

    when (file.extension) {
        "json" -> try {
            local.repo.spec = MmrpcSpec.fromJsonString(source)
        } catch (cause: Exception) {
            moduleLogger.e("Couldn't decode file: ${file.path}", cause)
            local.snackbar.showSnackbar("Couldn't decode file: ${file.path}")
        }

        "yaml", "yml" -> try {
            local.repo.spec = MmrpcSpec.fromYamlString(source)
        } catch (cause: Exception) {
            moduleLogger.e("Couldn't decode file: ${file.path}", cause)
            local.snackbar.showSnackbar("Couldn't decode file: ${file.path}")
        }

        else -> {
            moduleLogger.e("Unrecognized file extension: ${file.path}")
            local.snackbar.showSnackbar("Unrecognized file extension: ${file.path}")
        }
    }
}
