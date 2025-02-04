package org.cufy.mmrpc.editor.components.lib

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CompactNavigationDrawerItem(
    label: String,
    onClick: () -> Unit = {},
    selected: Boolean = false,
) {
    val child: @Composable () -> Unit = {
        Box(Modifier.fillMaxWidth().clickable { onClick() }) {
            Box(Modifier.padding(vertical = 5.dp, horizontal = 50.dp)) {
                Text(label, fontSize = 15.sp)
            }
        }
    }

    Column(Modifier.padding(horizontal = 20.dp)) {
        if (selected) {
            Card { child() }
        } else {
            Box(Modifier.clip(CardDefaults.shape)) { child() }
        }
    }
}
