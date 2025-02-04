package org.cufy.mmrpc.editor.scripts

import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.cufy.mmrpc.compact.CompactSpecSheet.Companion.toCompactSpecSheet
import org.cufy.mmrpc.compact.inflate
import org.cufy.mmrpc.editor.ClientLocal
import org.cufy.mmrpc.editor.ClientSpec
import org.cufy.mmrpc.editor.common.preferences.flowDataSpec

fun createSpecState(clientLocal: ClientLocal): StateFlow<ClientSpec> {
    return clientLocal.flowDataSpec()
        .map {
            ClientSpec(
                name = it.name,
                version = it.version,
                sections = it.sections,
                elements = it.elements
                    .toCompactSpecSheet()
                    .inflate()
                    .elements
            )
        }
        .stateIn(clientLocal.ioScope, SharingStarted.Eagerly, ClientSpec())
}
