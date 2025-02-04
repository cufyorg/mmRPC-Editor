package org.cufy.mmrpc.editor.components.lib

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CustomAccordion(
    modifier: Modifier = Modifier,
    expanded: Boolean,
    summary: @Composable RowScope.() -> Unit,
    content: @Composable ColumnScope.() -> Unit,
) {
    OutlinedCard(modifier) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 20.dp,
                        bottom = 20.dp,
                        start = 30.dp,
                        end = 20.dp
                    ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                summary()
            }

            AnimatedVisibility(expanded) {
                DashedHorizontalDivider()

                Column(
                    modifier = Modifier
                        .padding(
                            top = 10.dp,
                            start = 30.dp,
                            end = 30.dp,
                            bottom = 30.dp,
                        )
                ) {
                    content()
                }
            }
        }
    }
}
