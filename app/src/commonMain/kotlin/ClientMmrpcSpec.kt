package org.cufy.mmrpc.editor

import kotlinx.serialization.Serializable
import org.cufy.mmrpc.CanonicalName
import org.cufy.mmrpc.ElementDefinition

@Serializable
class ClientMmrpcSpec(
    val name: String = "[null]",
    val version: String = "[null]",
    val sections: List<CanonicalName> = emptyList(),
    val elements: List<ElementDefinition> = emptyList(),
) {
    val isEmpty by lazy {
        name == "[null]" && version == "[null]" &&
                sections.isEmpty() && elements.isEmpty()
    }

    val roots by lazy {
        val excludes = elements.asSequence()
            .map { it.canonicalName }
            .toSet()

        elements.asSequence()
            .map { it.namespace }
            .minus(excludes)
            .toSet()
    }

    val elementsInRoots = elements
        .filter { it.namespace in roots }
        .groupBy { it.namespace }

    val elementsNearestRoot = elements
        .associateWith { element ->
            val ens = element.namespace ?: return@associateWith null
            roots.filterNotNull()
                .filter { ens == it || ens in it }
                .maxOrNull()
        }
}
