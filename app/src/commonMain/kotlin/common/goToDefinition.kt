package org.cufy.mmrpc.editor.common

import org.cufy.mmrpc.ElementDefinition
import org.cufy.mmrpc.ProtocolDefinition
import org.cufy.mmrpc.editor.Local
import org.cufy.mmrpc.editor.MainNavController
import org.cufy.mmrpc.editor.MainRoute

context(local: Local, navCtrl: MainNavController)
fun goToDefinition(element: ElementDefinition) {
    val spec = local.repo.displaySpec

    when (element) {
        is ProtocolDefinition -> {
            val namespace = spec.elementsNearestRoot[element]

            namespace ?: return
            navCtrl.push(MainRoute.Protocol(namespace, element))
        }

        else -> {
            val namespace = spec.elementsNearestRoot[element]

            namespace ?: return
            navCtrl.push(MainRoute.Namespace(namespace, element))
        }
    }
}
