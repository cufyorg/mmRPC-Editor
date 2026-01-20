package org.cufy.mmrpc.editor.components.element.reference

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import org.cufy.mmrpc.ElementDefinition
import org.cufy.mmrpc.FieldDefinition
import org.cufy.mmrpc.editor.*
import org.cufy.mmrpc.editor.components.element.content.ElementContent
import org.cufy.mmrpc.editor.components.lib.PopupTooltipBox

@Composable
fun FieldElementReference(
    element: FieldDefinition,
    onElementClick: (ElementDefinition) -> Unit,
    modifier: Modifier = Modifier,
    highlighted: Boolean = false,
    isOverride: Boolean = false,
) {
    val modifiers = if (isOverride) "override" else null
    val actualKey = element.key ?: element.name

    Row(modifier) {
        PopupTooltipBox(
            tooltip = {
                ElementContent(element, onElementClick)
            },
            content = {
                SelectionContainer {
                    Row {
                        if (modifiers != null) {
                            Text(
                                text = modifiers + " ",
                                style = ELEMENT_STYLE_MEDIUM,
                                fontWeight = FontWeight.Bold,
                                color = ELEMENT_COLOR_FIELD_MODIFIERS,
                                fontFamily = FontFamily.Monospace,
                            )
                        }

                        if (highlighted) {
                            Text(
                                text = actualKey,
                                style = ELEMENT_STYLE_MEDIUM,
                                fontWeight = FontWeight.Bold,
                                color = ELEMENT_COLOR_PROP_HIGHLIGHTED,
                                fontFamily = FontFamily.Monospace,
                            )
                        } else {
                            Text(
                                text = actualKey,
                                style = ELEMENT_STYLE_MEDIUM,
                                color = ELEMENT_COLOR_PROP,
                                fontFamily = FontFamily.Monospace,
                            )
                        }
                    }
                }
            }
        )

        if (highlighted) {
            Text(
                text = ": ",
                style = ELEMENT_STYLE_MEDIUM,
                fontWeight = FontWeight.Bold,
                color = ELEMENT_COLOR_PROP_HIGHLIGHTED,
                fontFamily = FontFamily.Monospace,
            )
        } else {
            Text(
                text = ": ",
                style = ELEMENT_STYLE_MEDIUM,
                color = ELEMENT_COLOR_PROP,
                fontFamily = FontFamily.Monospace,
            )
        }

        ElementReference(element.type, onElementClick)

        element.default?.let { default ->
            Text(
                text = " = ",
                style = ELEMENT_STYLE_MEDIUM,
                color = ELEMENT_COLOR_PROP,
                fontFamily = FontFamily.Monospace,
            )

            Text(
                text = default.contentToString(),
                style = ELEMENT_STYLE_MEDIUM,
                color = ELEMENT_COLOR_VALUE,
                fontFamily = FontFamily.Monospace,
            )
        }
    }
}
