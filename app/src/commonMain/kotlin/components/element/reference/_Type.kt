package org.cufy.mmrpc.editor.components.element.reference

import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import org.cufy.mmrpc.ElementDefinition
import org.cufy.mmrpc.TypeDefinition
import org.cufy.mmrpc.editor.ELEMENT_COLOR_DEF
import org.cufy.mmrpc.editor.ELEMENT_STYLE_MEDIUM
import org.cufy.mmrpc.editor.components.element.content.ElementContent
import org.cufy.mmrpc.editor.components.lib.PopupTooltipBox

@Composable
fun TypeElementReference(
    element: TypeDefinition,
    types: List<TypeDefinition>,
    prefix: String,
    suffix: String,
    separator: String,
    isSeparatorBiased: Boolean,
    onElementClick: (ElementDefinition) -> Unit,
    modifier: Modifier,
) {
    PopupTooltipBox(
        modifier = modifier,
        tooltip = {
            ElementContent(element, onElementClick)
        },
        content = {
            // TODO check if is inlined
//            if (element.isAnonymous) {
//                Row {
//                    if (prefix.isNotBlank()) {
//                        Text(
//                            text = "$prefix ",
//                            style = ELEMENT_STYLE_MEDIUM,
//                            color = ELEMENT_COLOR_DEF,
//                            fontFamily = FontFamily.Monospace,
//                        )
//                    }
//
//                    for ((i, it) in types.withIndex()) {
//                        if (i != 0 && !isSeparatorBiased) {
//                            Text(
//                                text = " $separator ",
//                                style = ELEMENT_STYLE_MEDIUM,
//                                color = ELEMENT_COLOR_DEF,
//                                fontFamily = FontFamily.Monospace,
//                            )
//                        }
//
//                        if (i != 0 && isSeparatorBiased) {
//                            Text(
//                                text = "$separator ",
//                                style = ELEMENT_STYLE_MEDIUM,
//                                color = ELEMENT_COLOR_DEF,
//                                fontFamily = FontFamily.Monospace,
//                            )
//                        }
//
//                        ElementReference(it, onElementClick)
//                    }
//
//                    if (suffix.isNotBlank()) {
//                        Text(
//                            text = " $suffix",
//                            style = ELEMENT_STYLE_MEDIUM,
//                            color = ELEMENT_COLOR_DEF,
//                            fontFamily = FontFamily.Monospace,
//                        )
//                    }
//                }
//            }

            SelectionContainer {
                Text(
                    text = element.canonicalName.value,
                    style = ELEMENT_STYLE_MEDIUM,
                    color = ELEMENT_COLOR_DEF,
                    fontFamily = FontFamily.Monospace,
                )
            }
        }
    )
}
