package org.cufy.mmrpc.editor.components.element.attributes

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.cufy.mmrpc.ElementDefinition
import org.cufy.mmrpc.StructDefinition

@Composable
fun ElementAttributes(
    element: ElementDefinition,
    onElementClick: (ElementDefinition) -> Unit,
    modifier: Modifier = Modifier,
) {
    when (element) {
        is StructDefinition -> StructElementAttributes(element, onElementClick, modifier)
        else -> DefaultElementAttributes(element, onElementClick, modifier)
    }
}
