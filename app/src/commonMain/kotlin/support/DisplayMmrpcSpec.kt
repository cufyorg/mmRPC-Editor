package org.cufy.mmrpc.editor.support

import kotlinx.serialization.Serializable
import org.cufy.mmrpc.*
import org.cufy.mmrpc.compact.inflate

@Serializable
class DisplayMmrpcSpec(
    val name: String = "[null]",
    val version: String = "[null]",
    val sections: List<CanonicalName> = emptyList(),
    val elements: List<ElementDefinition> = emptyList(),
) {
    companion object {
        fun of(spec: MmrpcSpec?): DisplayMmrpcSpec {
            if (spec == null) return DisplayMmrpcSpec()
            return DisplayMmrpcSpec(
                name = spec.name,
                version = spec.version,
                sections = spec.sections,
                elements = spec.elements.asSequence()
                    .inflate(builtin.elements)
                    .flatMap { it.collect() }
                    .distinctBy { it.canonicalName }
                    .toList()
            )
        }
    }

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
