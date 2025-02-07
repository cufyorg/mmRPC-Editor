package org.cufy.mmrpc.editor.scripts

import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.cufy.mmrpc.builtin
import org.cufy.mmrpc.compact.inflate
import org.cufy.mmrpc.editor.ClientLocal
import org.cufy.mmrpc.editor.ClientMmrpcSpec
import org.cufy.mmrpc.editor.common.preferences.flowDataSpec

fun createSpecState(clientLocal: ClientLocal): StateFlow<ClientMmrpcSpec> {
    return clientLocal.flowDataSpec()
        .map { mmrpcSpec ->
            ClientMmrpcSpec(
                name = mmrpcSpec.name,
                version = mmrpcSpec.version,
                sections = mmrpcSpec.sections,
                elements = mmrpcSpec.elements.asSequence()
                    .inflate(builtin.elements)
                    .flatMap { it.collect() }
                    .distinctBy { it.canonicalName }
                    .toList()
            )
        }
        .stateIn(clientLocal.ioScope, SharingStarted.Eagerly, ClientMmrpcSpec())
}
