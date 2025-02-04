package org.cufy.mmrpc.editor.components.element.reference

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.cufy.mmrpc.*

@Composable
fun ElementReference(
    element: ElementDefinition,
    onElementClick: (ElementDefinition) -> Unit,
    modifier: Modifier = Modifier,
) {
    when (element) {
        is ConstDefinition -> ConstElementReference(element, onElementClick, modifier)
        is EnumDefinition -> EnumElementReference(element, onElementClick, modifier)
        is FieldDefinition -> FieldElementReference(element, onElementClick, modifier)
        is OptionalDefinition -> OptionalElementReference(element, onElementClick, modifier)

        is FaultDefinition -> DefaultElementReference(element, onElementClick, modifier)
        is MetadataDefinition -> DefaultElementReference(element, onElementClick, modifier)
        is ProtocolDefinition -> DefaultElementReference(element, onElementClick, modifier)
        is RoutineDefinition -> DefaultElementReference(element, onElementClick, modifier)
        is ScalarDefinition -> DefaultElementReference(element, onElementClick, modifier)
        is StructDefinition -> DefaultElementReference(element, onElementClick, modifier)

        is UnionDefinition ->
            TypeElementReference(
                element = element,
                types = element.types,
                prefix = "",
                suffix = "",
                separator = "|",
                isSeparatorBiased = false,
                onElementClick = onElementClick,
                modifier = modifier,
            )

        is InterDefinition ->
            TypeElementReference(
                element = element,
                types = element.types,
                prefix = "",
                suffix = "",
                separator = "&",
                isSeparatorBiased = false,
                onElementClick = onElementClick,
                modifier = modifier,
            )

        is TupleDefinition ->
            TypeElementReference(
                element = element,
                types = element.types,
                prefix = "(",
                suffix = ")",
                separator = ",",
                isSeparatorBiased = true,
                onElementClick = onElementClick,
                modifier = modifier,
            )

        is ArrayDefinition ->
            TypeElementReference(
                element = element,
                types = listOf(element.type),
                prefix = "[",
                suffix = "]",
                separator = ",",
                isSeparatorBiased = true,
                onElementClick = onElementClick,
                modifier = modifier,
            )
    }
}
