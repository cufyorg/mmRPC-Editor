package org.cufy.mmrpc.editor.components.element

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ExpandCircleDown
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import org.cufy.mmrpc.ElementDefinition
import org.cufy.mmrpc.RoutineDefinition
import org.cufy.mmrpc.editor.*
import org.cufy.mmrpc.editor.components.element.attributes.ElementAttributes
import org.cufy.mmrpc.editor.components.element.content.ElementContent
import org.cufy.mmrpc.editor.components.element.description.ElementDescription
import org.cufy.mmrpc.editor.components.lib.Accordion
import org.cufy.mmrpc.editor.components.lib.DashedHorizontalDivider
import org.cufy.mmrpc.editor.components.lib.PopupTooltipBox
import org.cufy.mmrpc.editor.util.COMM_CHANNEL
import org.cufy.mmrpc.editor.util.COMM_DIRECTION
import org.cufy.mmrpc.editor.util.COMM_SECURITY
import org.cufy.mmrpc.editor.util.COMM_STANDARD

@Composable
fun RoutineAccordion(
    routine: RoutineDefinition,
    expanded: Boolean,
    onExpandCollapseButtonClick: () -> Unit,
    onElementClick: (ElementDefinition) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val commChannel = routine.comm intersect COMM_CHANNEL
    val commDirection = routine.comm intersect COMM_DIRECTION
    val commSecurity = routine.comm intersect COMM_SECURITY
    val commOther = routine.comm - COMM_STANDARD

    Accordion(
        modifier = modifier,
        expanded = expanded,
        summary = {
            Column(Modifier.weight(10f)) {
                SelectionContainer {
                    Text(
                        text = routine.name,
                        style = ELEMENT_STYLE_LARGE,
                    )
                }

                SelectionContainer {
                    Text(
                        text = routine.canonicalName.value,
                        style = ELEMENT_STYLE_MEDIUM_LIGHT,
                        color = ELEMENT_COLOR_DEF,
                        fontFamily = FontFamily.Monospace,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }

            Spacer(Modifier.fillMaxWidth().weight(1f))

            for (comm in commChannel + commDirection) {
                Spacer(Modifier.width(COMMON_PADDING))

                AssistChip(
                    onClick = {},
                    label = { Text(comm.value) },
                )
            }

            Spacer(Modifier.width(COMMON_PADDING))

            IconButton({ onExpandCollapseButtonClick() }) {
                val anim by animateFloatAsState(if (expanded) 180f else 0f)

                Icon(Icons.Outlined.ExpandCircleDown, null, Modifier.rotate(anim))
            }
        },
        content = {
            ElementDescription(
                element = routine,
                onElementClick = onElementClick,
                placeholder = "NO DESCRIPTION",
                modifier = Modifier.defaultMinSize(
                    minHeight = 150.dp
                )
            )

            // Comm Section
            Spacer(Modifier.height(COMMON_PADDING * 2))
            DashedHorizontalDivider()
            Spacer(Modifier.height(COMMON_PADDING))

            Text(
                text = "Comm:",
                style = ELEMENT_STYLE_MEDIUM,
                fontWeight = FontWeight.Bold,
            )

            if (commChannel.isNotEmpty()) BasicAttribute(
                attributeName = "Channel",
                attributeValue = commChannel.joinToString(" | ") { it.value },
            )
            if (commDirection.isNotEmpty()) BasicAttribute(
                attributeName = "Direction",
                attributeValue = commDirection.joinToString(" | ") { it.value },
            )
            if (commSecurity.isNotEmpty()) BasicAttribute(
                attributeName = "Security",
                attributeValue = commSecurity.joinToString(" & ") { it.value },
            )
            if (commOther.isNotEmpty()) BasicAttribute(
                attributeName = "Other",
                attributeValue = commOther.joinToString(", ") { it.value },
            )

            // Fault Section
            if (routine.faults.isNotEmpty()) {
                Spacer(Modifier.height(COMMON_PADDING * 2))
                DashedHorizontalDivider()
                Spacer(Modifier.height(COMMON_PADDING))

                Text(
                    text = "Fault:",
                    style = ELEMENT_STYLE_MEDIUM,
                    fontWeight = FontWeight.Bold,
                )

                // faults
                for (fault in routine.faults) {
                    Spacer(Modifier.height(COMMON_PADDING / 2))

                    PopupTooltipBox(
                        tooltip = {
                            ElementContent(fault, onElementClick)
                        },
                        content = {
                            SelectionContainer {
                                Text(
                                    text = fault.name,
                                    style = ELEMENT_STYLE_MEDIUM,
                                    color = ELEMENT_COLOR_FAULT_NAME,
                                    fontFamily = FontFamily.Monospace,
                                )
                            }
                        },
                    )

                    ElementDescription(fault, onElementClick)
                }
            }

            // Prop Section
            Spacer(Modifier.height(COMMON_PADDING * 2))
            DashedHorizontalDivider()
            Spacer(Modifier.height(COMMON_PADDING))

            // Input Props
            if (routine.input.fields.isNotEmpty()) {
                PopupTooltipBox(
                    tooltip = {
                        ElementContent(routine.input, onElementClick)
                    },
                    content = {
                        Text(
                            text = "Input:",
                            style = ELEMENT_STYLE_MEDIUM,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                )

                ElementDescription(routine.input, onElementClick)
                ElementAttributes(routine.input, onElementClick)
            }

            Spacer(Modifier.height(COMMON_PADDING))

            // Output Props
            if (routine.output.fields.isNotEmpty()) {
                PopupTooltipBox(
                    tooltip = {
                        ElementContent(routine.output, onElementClick)
                    },
                    content = {
                        Text(
                            text = "Output:",
                            style = ELEMENT_STYLE_MEDIUM,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                )

                ElementDescription(routine.output, onElementClick)
                ElementAttributes(routine.output, onElementClick)
            }
        }
    )
}
