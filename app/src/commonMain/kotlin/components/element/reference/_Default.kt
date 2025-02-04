package org.cufy.mmrpc.editor.components.element.reference

import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import org.cufy.mmrpc.ElementDefinition
import org.cufy.mmrpc.editor.ELEMENT_COLOR_DEF
import org.cufy.mmrpc.editor.ELEMENT_STYLE_MEDIUM
import org.cufy.mmrpc.editor.components.element.content.ElementContent
import org.cufy.mmrpc.editor.components.lib.PopupTooltipBox

@Composable
fun DefaultElementReference(
    element: ElementDefinition,
    onElementClick: (ElementDefinition) -> Unit,
    modifier: Modifier = Modifier,
) {
    PopupTooltipBox(
        modifier = modifier,
        tooltip = {
            ElementContent(element, onElementClick)
        },
        content = {
            SelectionContainer {
                Text(
                    text = element.canonicalName.value,
                    style = ELEMENT_STYLE_MEDIUM,
                    color = ELEMENT_COLOR_DEF,
                    fontFamily = FontFamily.Monospace,
                )
            }
        },
    )
}
