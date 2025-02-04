package org.cufy.mmrpc.editor.components.element

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import org.cufy.mmrpc.editor.COMMON_PADDING
import org.cufy.mmrpc.editor.ELEMENT_COLOR_ATTR
import org.cufy.mmrpc.editor.ELEMENT_STYLE_MEDIUM_LIGHT

@Composable
fun BasicAttribute(
    attributeName: String,
    attributeValue: String,
    modifier: Modifier = Modifier
) {
    Row(modifier) {
        SelectionContainer {
            Text(
                text = attributeName,
                style = ELEMENT_STYLE_MEDIUM_LIGHT,
                color = ELEMENT_COLOR_ATTR,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
            )
        }

        Text(
            text = ": ",
            style = ELEMENT_STYLE_MEDIUM_LIGHT,
            color = ELEMENT_COLOR_ATTR,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
        )

        Spacer(Modifier.width(COMMON_PADDING))

        SelectionContainer {
            Text(
                text = attributeValue,
                style = ELEMENT_STYLE_MEDIUM_LIGHT,
                color = ELEMENT_COLOR_ATTR,
                fontFamily = FontFamily.Monospace,
            )
        }
    }
}
