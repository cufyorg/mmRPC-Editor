package org.cufy.mmrpc.editor.components.lib

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.TooltipDefaults.rememberPlainTooltipPositionProvider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.*
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupPositionProvider
import androidx.compose.ui.window.PopupProperties
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopupTooltipBox(
    modifier: Modifier = Modifier,

    anchor: Alignment = Alignment.BottomCenter,
    alignment: Alignment = Alignment.BottomCenter,
    offset: DpOffset = DpOffset.Zero,

    tooltip: @Composable () -> Unit,
    content: @Composable () -> Unit,
) {
    var isExpanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .clickable(role = Role.Button) { isExpanded = true }
            .then(modifier)
    ) {
        content()

        if (isExpanded) {
            val provider = rememberPlainTooltipPositionProvider()
            var offsetX by remember { mutableStateOf(0f) }
            var offsetY by remember { mutableStateOf(0f) }

            Popup(
                popupPositionProvider = object : PopupPositionProvider {
                    override fun calculatePosition(
                        anchorBounds: IntRect,
                        windowSize: IntSize,
                        layoutDirection: LayoutDirection,
                        popupContentSize: IntSize
                    ): IntOffset {
                        val (x, y) = provider.calculatePosition(
                            anchorBounds = anchorBounds,
                            windowSize = windowSize,
                            layoutDirection = layoutDirection,
                            popupContentSize = popupContentSize,
                        )

                        return IntOffset(
                            x = x + offsetX.roundToInt(),
                            y = y + offsetY.roundToInt()
                        )
                    }
                },
                onDismissRequest = { isExpanded = false },
                properties = PopupProperties(
                    focusable = true,
                    dismissOnClickOutside = true,
                ),
                content = {
                    Box(
                        modifier = Modifier.padding(10.dp)
                            .pointerInput(Unit) {
                                detectDragGestures { change, dragAmount ->
                                    change.consume()
                                    offsetX += dragAmount.x
                                    offsetY += dragAmount.y
                                }
                            }
                    ) {
                        tooltip()
                    }
                },
            )
        }
    }
}

@Composable
fun PopupTooltip(
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.extraSmall,
    colors: CardColors = CardDefaults.elevatedCardColors(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
    ),
    elevation: CardElevation = CardDefaults.elevatedCardElevation(
        defaultElevation = 6.0.dp,
        focusedElevation = 8.0.dp,
        hoveredElevation = 8.0.dp,
        draggedElevation = 10.0.dp,
        pressedElevation = 10.0.dp,
        disabledElevation = 6.0.dp,
    ),
    content: @Composable ColumnScope.() -> Unit
) {
    ElevatedCard(
        modifier = modifier,
        shape = shape,
        colors = colors,
        elevation = elevation,
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .defaultMinSize(500.dp, 500.dp)
        ) {
            content()
        }
    }
}
