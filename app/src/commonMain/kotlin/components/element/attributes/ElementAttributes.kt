package org.cufy.mmrpc.editor.components.element.attributes

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.cufy.mmrpc.ElementDefinition
import org.cufy.mmrpc.MetadataDefinition
import org.cufy.mmrpc.StructDefinition
import org.cufy.mmrpc.TraitDefinition

@Composable
fun ElementAttributes(
    element: ElementDefinition,
    onElementClick: (ElementDefinition) -> Unit,
    modifier: Modifier = Modifier,
) {
    when (element) {
        is StructDefinition -> StructElementAttributes(element, onElementClick, modifier)
        is TraitDefinition -> TraitElementAttributes(element, onElementClick, modifier)
        is MetadataDefinition -> MetadataElementAttributes(element, onElementClick, modifier)
        else -> DefaultElementAttributes(element, onElementClick, modifier)
    }
}
