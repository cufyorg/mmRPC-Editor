package org.cufy.mmrpc.editor.components.element.content

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.cufy.mmrpc.ElementDefinition
import org.cufy.mmrpc.editor.COMMON_PADDING
import org.cufy.mmrpc.editor.components.element.attributes.ElementAttributes
import org.cufy.mmrpc.editor.components.element.description.ElementDescription
import org.cufy.mmrpc.editor.components.element.signature.ElementSignature
import org.cufy.mmrpc.editor.components.lib.PopupTooltip

@Composable
fun ElementContent(
    element: ElementDefinition,
    onElementClick: (ElementDefinition) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    PopupTooltip(modifier) {
        ElementSignature(element, onElementClick)
        Spacer(Modifier.height(COMMON_PADDING))
        ElementDescription(element, onElementClick)
        Spacer(Modifier.height(COMMON_PADDING))
        ElementAttributes(element, onElementClick)
    }
}
