package org.cufy.mmrpc.editor.components.wrapper

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.type
import androidx.compose.ui.input.pointer.*
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import kotlinx.coroutines.launch
import org.cufy.mmrpc.editor.Local
import org.cufy.mmrpc.editor.MainNavController

@OptIn(ExperimentalComposeUiApi::class)
@Composable
context(local: Local, app: ApplicationScope, navCtrl: MainNavController)
fun DesktopUniWindowCompat(content: @Composable FrameWindowScope.() -> Unit) {
    val coroutineScope = rememberCoroutineScope()
    val windowState = rememberWindowState(
        size = DpSize(3840.dp, 2160.dp),
        position = WindowPosition(Alignment.Center)
    )

    var isCtrlLeftPressed by remember { mutableStateOf(false) }
    var isCtrlRightPressed by remember { mutableStateOf(false) }
    val isCtrlPressed by derivedStateOf { isCtrlLeftPressed || isCtrlRightPressed }

    fun onLeaveRequest() = coroutineScope.launch {
        val result = local.snackbar.showSnackbar(
            message = "Exit the application?",
            actionLabel = "Yes",
            withDismissAction = true,
            duration = SnackbarDuration.Short,
        )

        if (result == SnackbarResult.ActionPerformed)
            app.exitApplication()
    }

    fun onScrollPointerEvent(it: PointerEvent) {
        if (isCtrlPressed) for (change in it.changes) when {
            change.scrollDelta.y > .0f ->
                local.repo.uiScale -= 10

            change.scrollDelta.y < .0f ->
                local.repo.uiScale += 10
        }
    }

    fun onPressPointerEvent(it: PointerEvent) {
        if (it.button?.index == 5)
            if (!navCtrl.back())
                onLeaveRequest()

        if (it.button?.index == 6)
            navCtrl.forward()
    }

    Window(
        onCloseRequest = app::exitApplication,
        title = "Mmrpc Editor",
        state = windowState,
        onKeyEvent = {
            if (it.key == Key.CtrlLeft && it.type == KeyEventType.KeyDown) {
                isCtrlLeftPressed = true
                return@Window true
            }
            if (it.key == Key.CtrlLeft && it.type == KeyEventType.KeyUp) {
                isCtrlLeftPressed = false
                return@Window true
            }
            if (it.key == Key.CtrlRight && it.type == KeyEventType.KeyDown) {
                isCtrlRightPressed = true
                return@Window true
            }
            if (it.key == Key.CtrlRight && it.type == KeyEventType.KeyUp) {
                isCtrlRightPressed = false
                return@Window true
            }

            return@Window false
        },
    ) {
        val focusManager = LocalFocusManager.current

        Box(
            Modifier
                .onPointerEvent(PointerEventType.Scroll, PointerEventPass.Main) {
                    onScrollPointerEvent(it)
                }
                .onPointerEvent(PointerEventType.Press, PointerEventPass.Main) {
                    onPressPointerEvent(it)
                }
                .pointerInput(Unit) {
                    detectTapGestures {
                        focusManager.clearFocus()
                    }
                }
                .fillMaxSize()
        ) {
            content()
        }
    }
}
