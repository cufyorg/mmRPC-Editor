package org.cufy.mmrpc.editor.components.page.empty

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.cufy.mmrpc.editor.ClientLocal
import org.cufy.mmrpc.editor.common.openMmrpcSpec

@Composable
fun EmptyPage(
    clientLocal: ClientLocal,
    modifier: Modifier = Modifier,
) {
    val coroutineScope = rememberCoroutineScope()

    fun onOpenSchemaClick() = coroutineScope.launch { openMmrpcSpec(clientLocal) }

    Scaffold(
        modifier = Modifier.fillMaxWidth().then(modifier),
        snackbarHost = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomEnd,
                content = { SnackbarHost(clientLocal.snackbar) }
            )
        },
    ) {
        Box(Modifier.fillMaxSize(), Alignment.Center) {
            Button(::onOpenSchemaClick) {
                Text(
                    text = "Open Schema",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Black,
                )
            }
        }
    }
}
