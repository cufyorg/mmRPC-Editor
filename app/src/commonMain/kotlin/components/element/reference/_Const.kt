package org.cufy.mmrpc.editor.components.element.reference

import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import org.cufy.mmrpc.ConstDefinition
import org.cufy.mmrpc.ElementDefinition
import org.cufy.mmrpc.editor.ELEMENT_COLOR_VALUE
import org.cufy.mmrpc.editor.ELEMENT_STYLE_MEDIUM
import org.cufy.mmrpc.editor.components.element.content.ElementContent
import org.cufy.mmrpc.editor.components.lib.PopupTooltipBox

@Composable
fun ConstElementReference(
    element: ConstDefinition,
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
                    text = element.value.contentToString(),
                    style = ELEMENT_STYLE_MEDIUM,
                    color = ELEMENT_COLOR_VALUE,
                    fontFamily = FontFamily.Monospace,
                )
            }
        }
    )
}
