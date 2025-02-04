package org.cufy.mmrpc.editor.scripts

import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.cufy.mmrpc.editor.ClientLocal
import org.cufy.mmrpc.editor.ClientSpec
import org.cufy.mmrpc.editor.ClientSpec.Companion.toClientSpec
import org.cufy.mmrpc.editor.common.preferences.flowDataSpec

fun createSpecState(clientLocal: ClientLocal): StateFlow<ClientSpec> {
    return clientLocal.flowDataSpec(clientLocal.ioScope)
        .map { it.toClientSpec() }
        .stateIn(clientLocal.ioScope, SharingStarted.Eagerly, ClientSpec())
}
