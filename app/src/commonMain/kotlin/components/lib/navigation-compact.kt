package org.cufy.mmrpc.editor.components.lib

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val HORIZONTAL_PADDING = 20.dp
private val VERTICAL_PADDING = .0.dp

private val ITEM_TITLE_FONT_SIZE = 15.sp
private val ITEM_SUMMARY_FONT_SIZE = 14.sp
private val ITEM_LEADING_TRAILING_WIDTH = 45.dp
private val ITEM_CONTENT_PADDING = 5.dp

private const val SUMMARY_OPACITY = .5f

@Composable
fun NavigationCompactItem(
    title: String,
    summary: String? = null,
    onClick: () -> Unit = {},
    active: Boolean = false,
    leadingIcon: @Composable () -> Unit = {},
    trailingIcon: @Composable () -> Unit = {},
) {
    val child: @Composable () -> Unit = {
        Box(Modifier.fillMaxWidth().clickable { onClick() }) {
            Row(Modifier.padding(ITEM_CONTENT_PADDING), verticalAlignment = Alignment.CenterVertically) {
                Box(Modifier.width(ITEM_LEADING_TRAILING_WIDTH), contentAlignment = Alignment.CenterStart) {
                    leadingIcon()
                }

                Column(Modifier.fillMaxWidth().weight(1f), Arrangement.Center) {
                    Text(
                        text = title,
                        fontSize = ITEM_TITLE_FONT_SIZE,
                    )

                    if (summary != null) {
                        Text(
                            text = summary,
                            fontSize = ITEM_SUMMARY_FONT_SIZE,
                            color = MaterialTheme.colorScheme.onBackground.copy(SUMMARY_OPACITY),
                        )
                    }
                }

                Box(Modifier.width(ITEM_LEADING_TRAILING_WIDTH), contentAlignment = Alignment.CenterEnd) {
                    trailingIcon()
                }
            }
        }
    }

    Column(Modifier.padding(horizontal = HORIZONTAL_PADDING)) {
        if (active) {
            Card(Modifier.padding(vertical = VERTICAL_PADDING)) {
                child()
            }
        } else {
            Box(Modifier.padding(vertical = VERTICAL_PADDING).clip(CardDefaults.shape)) {
                child()
            }
        }
    }
}
