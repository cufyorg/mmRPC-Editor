package org.cufy.mmrpc.editor.components.element.description

import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mikepenz.markdown.compose.Markdown
import com.mikepenz.markdown.m3.markdownColor
import com.mikepenz.markdown.m3.markdownTypography
import org.cufy.mmrpc.ElementDefinition
import org.cufy.mmrpc.editor.ELEMENT_COLOR_DEF
import org.cufy.mmrpc.editor.ELEMENT_COLOR_DESC
import org.intellij.markdown.flavours.space.SFMFlavourDescriptor

private val LINE_SPAN_CHARS = setOf(
    '-', '#', '+',
)

@Composable
fun ElementDescription(
    element: ElementDefinition,
    onElementClick: (ElementDefinition) -> Unit = {},
    placeholder: String = "",
    modifier: Modifier = Modifier,
) {
    SelectionContainer {
        Markdown(
            content = buildString {
                // fixme this rendering function is garbage

                if (element.description.isBlank()) {
                    append(placeholder)
                    return@buildString
                }

                var isLineStart = true
                var isBulletPoint = false
                for (line in element.description.lineSequence()) {
                    if (line.isBlank()) {
                        appendLine()
                        isBulletPoint = false
                        isLineStart = true
                        continue
                    }

                    val lineTrimmed = line.trim()

                    if (lineTrimmed[0] in LINE_SPAN_CHARS) {
                        if (!isBulletPoint && !isLineStart)
                            appendLine()
                        append(lineTrimmed)
                        appendLine()
                        isBulletPoint = true
                        continue
                    }

                    when {
                        isBulletPoint -> {
                            append("        ")
                            append(lineTrimmed)
                            appendLine()
                        }

                        isLineStart -> {
                            if (isBulletPoint)
                                appendLine()

                            append(lineTrimmed)
                            isLineStart = false
                            isBulletPoint = false
                        }

                        else -> {
                            append(' ')
                            append(lineTrimmed)
                        }
                    }
                }
            },
            colors = markdownColor(
                text = ELEMENT_COLOR_DESC,
                codeText = ELEMENT_COLOR_DEF,
                inlineCodeText = ELEMENT_COLOR_DEF,
                linkText = ELEMENT_COLOR_DEF,
            ),
            typography = markdownTypography(),
            flavour = SFMFlavourDescriptor(),
            modifier = Modifier.widthIn(max = 1500.dp).then(modifier),
        )
    }
}
