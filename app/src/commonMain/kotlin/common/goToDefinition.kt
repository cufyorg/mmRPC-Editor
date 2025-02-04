package org.cufy.mmrpc.editor.common

import org.cufy.mmrpc.ElementDefinition
import org.cufy.mmrpc.ProtocolDefinition
import org.cufy.mmrpc.editor.ClientLocal
import org.cufy.mmrpc.editor.NamespacePage
import org.cufy.mmrpc.editor.ProtocolPage

fun ClientLocal.goToDefinition(element: ElementDefinition) {
    val spec = specState.value

    when (element) {
        is ProtocolDefinition -> {
            val namespace = spec.elementsNearestRoot[element]

            namespace ?: return
            navController.push(ProtocolPage(namespace, element))
        }

        else -> {
            val namespace = spec.elementsNearestRoot[element]

            namespace ?: return
            navController.push(NamespacePage(namespace, element))
        }
    }
}
