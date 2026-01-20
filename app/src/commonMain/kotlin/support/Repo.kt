package org.cufy.mmrpc.editor.support

import androidx.compose.runtime.*
import kotlinx.serialization.json.JsonElement
import org.cufy.json.asIntOrNull
import org.cufy.json.asStringOrNull
import org.cufy.json.set
import org.cufy.mmrpc.MmrpcSpec
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class Repo {
    companion object {
        private const val PK_UI_SCALE = "ui.scale"
        private const val PK_UI_COLORS = "ui.colors"

        const val UI_SCALE_MIN = 50
        const val UI_SCALE_MAX = 300
        const val UI_SCALE_DEFAULT = 100
    }

    val data = mutableStateMapOf<String, JsonElement>()

    var spec by mutableStateOf<MmrpcSpec?>(null)
    val displaySpec by derivedStateOf {
        DisplayMmrpcSpec.of(spec)
    }

    var uiScale by property(
        get = { data[PK_UI_SCALE]?.asIntOrNull ?: UI_SCALE_DEFAULT },
        set = { data[PK_UI_SCALE] = it.coerceIn(UI_SCALE_MIN..UI_SCALE_MAX) },
    )
    var uiColors by property(
        get = { data[PK_UI_COLORS]?.asStringOrNull },
        set = { data[PK_UI_COLORS] = it },
    )
}

private fun <T> property(get: () -> T, set: (T) -> Unit) =
    object : ReadWriteProperty<Any?, T> {
        private val _cache by derivedStateOf { get() }
        override fun getValue(thisRef: Any?, property: KProperty<*>) = _cache
        override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) = set(value)
    }
