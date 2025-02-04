package org.cufy.mmrpc.editor.components.wrapper

import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import kotlinx.coroutines.launch
import org.cufy.mmrpc.editor.ClientLocal

@Composable
fun AndroidClientWindowCompat(
    clientLocal: ClientLocal,
    activity: ComponentActivity,
    content: @Composable () -> Unit,
) {
    val focusManager = LocalFocusManager.current

    val coroutineScope = rememberCoroutineScope()

    fun onLeaveRequest() = coroutineScope.launch {
        val result = clientLocal.snackbar.showSnackbar(
            message = "Exit the application?",
            actionLabel = "Yes",
            withDismissAction = true,
            duration = SnackbarDuration.Short,
        )

        if (result == SnackbarResult.ActionPerformed)
            activity.finish()
    }

    BackHandler {
        if (!clientLocal.navController.back())
            onLeaveRequest()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures {
                    focusManager.clearFocus()
                }
            }
    ) {
        content()
    }
}
