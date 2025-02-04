package org.cufy.mmrpc.editor.components.element.signature

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.cufy.mmrpc.*

@Composable
fun ElementSignature(
    element: ElementDefinition,
    onElementClick: (ElementDefinition) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    when (element) {
        is ConstDefinition -> ConstElementSignature(element, onElementClick, modifier)
        is EnumDefinition -> EnumElementSignature(element, onElementClick, modifier)
        is FieldDefinition -> FieldElementSignature(element, onElementClick, modifier)

        is FaultDefinition -> DefaultElementSignature("fault", element, onElementClick, modifier)
        is ScalarDefinition -> DefaultElementSignature("scalar", element, onElementClick, modifier)
        is ProtocolDefinition -> DefaultElementSignature("protocol", element, onElementClick, modifier)
        is RoutineDefinition -> DefaultElementSignature("routine", element, onElementClick, modifier)
        is StructDefinition -> DefaultElementSignature("struct", element, onElementClick, modifier)
        is MetadataDefinition -> DefaultElementSignature("metadata", element, onElementClick, modifier)
        is OptionalDefinition -> DefaultElementSignature("optional", element, onElementClick, modifier)

        is TupleDefinition ->
            TupleOrArrayElementSignature(
                element = element,
                types = element.types,
                prefix = "(",
                suffix = ")",
                separator = ",",
                onElementClick = onElementClick,
                modifier = modifier,
            )

        is ArrayDefinition ->
            TupleOrArrayElementSignature(
                element = element,
                types = listOf(element.type),
                prefix = "[",
                suffix = "]",
                separator = ",",
                onElementClick = onElementClick,
                modifier = modifier,
            )

        is UnionDefinition ->
            UnionOrInterElementSignature(
                element = element,
                types = element.types,
                separator = "|",
                onElementClick = onElementClick,
                modifier = modifier,
            )

        is InterDefinition ->
            UnionOrInterElementSignature(
                element = element,
                types = element.types,
                separator = "&",
                onElementClick = onElementClick,
                modifier = modifier,
            )
    }
}
