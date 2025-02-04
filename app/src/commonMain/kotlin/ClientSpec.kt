package org.cufy.mmrpc.editor

import kotlinx.serialization.Serializable
import org.cufy.mmrpc.CanonicalName
import org.cufy.mmrpc.ElementDefinition
import org.cufy.mmrpc.MmrpcSpec
import org.cufy.mmrpc.SpecSheet.Companion.toSpecSheet
import org.cufy.mmrpc.compact.CompactSpecSheet.Companion.toCompactSpecSheet
import org.cufy.mmrpc.compact.inflate
import org.cufy.mmrpc.compact.toCompact

@Serializable
class ClientSpec(
    val name: String = "[null]",
    val version: String = "[null]",
    val sections: List<CanonicalName> = emptyList(),
    val elements: Set<ElementDefinition> = emptySet(),
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

    companion object {
        fun ClientSpec.toMmrpcSpec(): MmrpcSpec {
            return MmrpcSpec(
                name = name,
                version = version,
                sections = sections,
                elements = elements
                    .toSpecSheet()
                    .toCompact()
                    .elements
            )
        }

        fun MmrpcSpec.toClientSpec(): ClientSpec {
            return ClientSpec(
                name = name,
                version = version,
                sections = sections,
                elements = elements
                    .toCompactSpecSheet()
                    .inflate()
                    .elements
            )
        }
    }
}
