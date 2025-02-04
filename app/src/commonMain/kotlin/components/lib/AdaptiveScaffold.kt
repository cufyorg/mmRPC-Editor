package org.cufy.mmrpc.editor.components.lib

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class AdaptiveScaffoldState internal constructor(
    val drawerState: DrawerState,
    internal val isDrawerPinnedState: MutableState<Boolean>
) {
    val isDrawerPinned: Boolean get() = isDrawerPinnedState.value
}

@Composable
fun rememberAdaptiveScaffoldState(
    drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed),
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }
): AdaptiveScaffoldState {
    return AdaptiveScaffoldState(
        drawerState = drawerState,
        isDrawerPinnedState = remember {
            mutableStateOf(false)
        }
    )
}

@Composable
fun AdaptiveScaffold(
    modifier: Modifier = Modifier,
    minWidth: Dp = 1680.dp,
    scaffoldState: AdaptiveScaffoldState = rememberAdaptiveScaffoldState(),

    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    snackbarHost: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,

    drawerContent: @Composable () -> Unit = {},
    drawerGesturesEnabled: Boolean = true,
    drawerScrimColor: Color = DrawerDefaults.scrimColor,

    containerColor: Color = MaterialTheme.colorScheme.background,
    contentColor: Color = contentColorFor(containerColor),
    contentWindowInsets: WindowInsets = ScaffoldDefaults.contentWindowInsets,
    content: @Composable (PaddingValues) -> Unit
) {
    BoxWithConstraints {
        val isDrawerPinned = maxWidth >= minWidth

        LaunchedEffect(isDrawerPinned) {
            scaffoldState.isDrawerPinnedState.value = isDrawerPinned
            if (isDrawerPinned)
                scaffoldState.drawerState.close()
        }

        ModalNavigationDrawer(
            modifier = modifier,
            drawerState = scaffoldState.drawerState,
            gesturesEnabled = drawerGesturesEnabled &&
                    (!isDrawerPinned || scaffoldState.drawerState.isOpen),
            scrimColor = drawerScrimColor,
            drawerContent = drawerContent,
            content = {
                Row {
                    AnimatedVisibility(isDrawerPinned) {
                        drawerContent()
                    }

                    Scaffold(
                        modifier = Modifier,
                        topBar = topBar,
                        bottomBar = bottomBar,
                        snackbarHost = snackbarHost,
                        floatingActionButton = floatingActionButton,
                        floatingActionButtonPosition = floatingActionButtonPosition,
                        containerColor = containerColor,
                        contentColor = contentColor,
                        contentWindowInsets = contentWindowInsets,
                        content = content,
                    )
                }
            },
        )
    }
}
