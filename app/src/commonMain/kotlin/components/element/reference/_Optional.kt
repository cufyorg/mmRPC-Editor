package org.cufy.mmrpc.editor.components.element.reference

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import org.cufy.mmrpc.ElementDefinition
import org.cufy.mmrpc.OptionalDefinition
import org.cufy.mmrpc.editor.ELEMENT_COLOR_DEF
import org.cufy.mmrpc.editor.ELEMENT_STYLE_MEDIUM

@Composable
fun OptionalElementReference(
    element: OptionalDefinition,
    onElementClick: (ElementDefinition) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(modifier) {
        ElementReference(element.type, onElementClick)

        Text(
            text = "?",
            style = ELEMENT_STYLE_MEDIUM,
            color = ELEMENT_COLOR_DEF,
            fontFamily = FontFamily.Monospace,
        )
    }
}
