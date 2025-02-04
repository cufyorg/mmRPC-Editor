package org.cufy.mmrpc.editor.components.element.signature

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import org.cufy.mmrpc.ConstDefinition
import org.cufy.mmrpc.ElementDefinition
import org.cufy.mmrpc.editor.ELEMENT_COLOR_DEF
import org.cufy.mmrpc.editor.ELEMENT_COLOR_VALUE
import org.cufy.mmrpc.editor.ELEMENT_STYLE_MEDIUM

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ConstElementSignature(
    element: ConstDefinition,
    onElementClick: (ElementDefinition) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    FlowRow(modifier) {
        Text(
            text = "const ",
            style = ELEMENT_STYLE_MEDIUM,
            color = ELEMENT_COLOR_DEF,
            fontFamily = FontFamily.Monospace,
        )

        SelectionContainer {
            Text(
                text = element.canonicalName.value,
                style = ELEMENT_STYLE_MEDIUM,
                color = ELEMENT_COLOR_DEF,
                fontFamily = FontFamily.Monospace,
                modifier = Modifier.clickable {
                    onElementClick(element)
                }
            )
        }

        Text(
            text = " = ",
            style = ELEMENT_STYLE_MEDIUM,
            color = ELEMENT_COLOR_DEF,
            fontFamily = FontFamily.Monospace,
        )

        SelectionContainer {
            Text(
                text = element.value.contentToString(),
                style = ELEMENT_STYLE_MEDIUM,
                color = ELEMENT_COLOR_VALUE,
                fontFamily = FontFamily.Monospace,
            )
        }
    }
}
