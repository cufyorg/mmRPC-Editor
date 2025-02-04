package org.cufy.mmrpc.editor.common.preferences

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import org.cufy.json.LenientJson
import org.cufy.json.asIntOrNull
import org.cufy.json.asStringOrNull
import org.cufy.json.deserializeOrNull
import org.cufy.mmrpc.MmrpcSpec
import org.cufy.mmrpc.editor.*

fun ClientLocal.flowUiScaleInOrDefault(coroutineScope: CoroutineScope): StateFlow<Int> {
    return dataStore.data
        .mapNotNull { it[PK_UI_SCALE]?.asIntOrNull }
        .stateIn(
            coroutineScope,
            SharingStarted.Eagerly,
            UI_SCALE_DEFAULT,
        )
}

fun ClientLocal.flowUiColorsInOrDefault(coroutineScope: CoroutineScope): StateFlow<String> {
    return dataStore.data
        .mapNotNull { it[PK_UI_COLORS]?.asStringOrNull }
        .stateIn(
            coroutineScope,
            SharingStarted.Eagerly,
            UI_COLORS_DEFAULT,
        )
}

fun ClientLocal.flowDataSpec(coroutineScope: CoroutineScope): Flow<MmrpcSpec> {
    return dataStore.data
        .mapNotNull { it[PK_DATA_SPEC]?.deserializeOrNull<MmrpcSpec>(LenientJson) }
}

val ClientLocal.uiScale get() = dataStore.data.value[PK_UI_SCALE]?.asIntOrNull ?: UI_SCALE_DEFAULT
val ClientLocal.uiColors get() = dataStore.data.value[PK_UI_COLORS]?.asStringOrNull ?: UI_COLORS_DEFAULT
