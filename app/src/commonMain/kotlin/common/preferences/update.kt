package org.cufy.mmrpc.editor.common.preferences

import net.lsafer.sundry.storage.edit
import org.cufy.json.asIntOrNull
import org.cufy.json.serializeToJsonObject
import org.cufy.json.set
import org.cufy.mmrpc.MmrpcSpec
import org.cufy.mmrpc.editor.*

fun ClientLocal.updateUiColors(value: String) {
    dataStore.edit { it[PK_UI_COLORS] = value }
}

fun ClientLocal.updateUiScale(block: (Int) -> Int) {
    dataStore.edit {
        val current = it[PK_UI_SCALE]?.asIntOrNull ?: 100
        it[PK_UI_SCALE] = block(current)
            .coerceIn(UI_SCALE_MIN..UI_SCALE_MAX)
    }
}

fun ClientLocal.updateDataSpec(value: MmrpcSpec) {
    dataStore.edit { it[PK_DATA_SPEC] = value.serializeToJsonObject() }
}
