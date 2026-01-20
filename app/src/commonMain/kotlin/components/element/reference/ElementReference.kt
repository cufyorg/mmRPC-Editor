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
        is ArrayDefinition -> ArrayElementReference(element, onElementClick, modifier)
        is MapDefinition -> MapElementReference(element, onElementClick, modifier)
        is ConstDefinition -> ConstElementReference(element, onElementClick, modifier)
        is EnumDefinition -> EnumElementReference(element, onElementClick, modifier)
        is FieldDefinition -> FieldElementReference(element, onElementClick, modifier)
        is OptionalDefinition -> OptionalElementReference(element, onElementClick, modifier)
        is TupleDefinition -> TupleElementReference(element, onElementClick, modifier)
        is UnionDefinition -> UnionElementReference(element, onElementClick, modifier)

        is FaultDefinition -> DefaultElementReference(element, onElementClick, modifier)
        is MetadataDefinition -> DefaultElementReference(element, onElementClick, modifier)
        is ProtocolDefinition -> DefaultElementReference(element, onElementClick, modifier)
        is RoutineDefinition -> DefaultElementReference(element, onElementClick, modifier)
        is ScalarDefinition -> DefaultElementReference(element, onElementClick, modifier)
        is StructDefinition -> DefaultElementReference(element, onElementClick, modifier)
        is TraitDefinition -> DefaultElementReference(element, onElementClick, modifier)
    }
}
