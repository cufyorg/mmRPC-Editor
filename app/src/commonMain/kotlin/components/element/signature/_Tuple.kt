package org.cufy.mmrpc.editor.components.element.signature

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import org.cufy.mmrpc.ElementDefinition
import org.cufy.mmrpc.TupleDefinition
import org.cufy.mmrpc.editor.COMMON_PADDING
import org.cufy.mmrpc.editor.ELEMENT_COLOR_DEF
import org.cufy.mmrpc.editor.ELEMENT_STYLE_MEDIUM
import org.cufy.mmrpc.editor.components.element.reference.ElementReference

@Composable
fun TupleElementSignature(
    element: TupleDefinition,
    onElementClick: (ElementDefinition) -> Unit,
    modifier: Modifier,
) {
    Column(modifier) {
        Row {
            Text(
                text = "type ",
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
                text = buildString {
                    append(" = (")

                    if (element.types.isEmpty())
                        append(")")
                },
                style = ELEMENT_STYLE_MEDIUM,
                color = ELEMENT_COLOR_DEF,
                fontFamily = FontFamily.Monospace,
            )
        }

        for (type in element.types) {
            Row(Modifier.padding(start = COMMON_PADDING)) {
                ElementReference(type, onElementClick)

                Text(
                    text = ",",
                    style = ELEMENT_STYLE_MEDIUM,
                    color = ELEMENT_COLOR_DEF,
                    fontFamily = FontFamily.Monospace,
                )
            }
        }

        if (element.types.isNotEmpty()) {
            Text(
                text = ")",
                style = ELEMENT_STYLE_MEDIUM,
                color = ELEMENT_COLOR_DEF,
                fontFamily = FontFamily.Monospace,
            )
        }
    }
}
