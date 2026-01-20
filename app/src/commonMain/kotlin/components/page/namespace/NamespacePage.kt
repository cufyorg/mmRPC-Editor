package org.cufy.mmrpc.editor.components.page.namespace

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import kotlinx.coroutines.launch
import org.cufy.mmrpc.ProtocolDefinition
import org.cufy.mmrpc.editor.*
import org.cufy.mmrpc.editor.common.goToDefinition
import org.cufy.mmrpc.editor.components.element.attributes.ElementAttributes
import org.cufy.mmrpc.editor.components.element.description.ElementDescription
import org.cufy.mmrpc.editor.components.element.signature.ElementSignature
import org.cufy.mmrpc.editor.components.scaffold.ClientScaffold
import kotlin.math.roundToInt

@Composable
context(
    local: Local,
    navCtrl: MainNavController,
    route: MainRoute.Namespace,
)
fun NamespacePage(modifier: Modifier = Modifier) {
    val spec = local.repo.displaySpec
    val elements = remember(route) { spec.elementsInRoots[route.namespace] ?: emptyList() }
    var focusOnElementReady by remember { mutableStateOf(false) }
    var focusOnElementYPosition by remember { mutableFloatStateOf(0f) }
    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberScrollState()

    LaunchedEffect(focusOnElementReady) {
        if (focusOnElementReady) {
            coroutineScope.launch {
                scrollState.animateScrollTo(focusOnElementYPosition.roundToInt())
            }
        }
    }

    ClientScaffold(modifier) {
        Column(
            modifier = Modifier
                .width(COMMON_CONTENT_WIDTH)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(COMMON_PADDING),
        ) {
            for (element in elements.filter { !it.canonicalName.isAnonymous }) {
                OutlinedCard(
                    modifier = when (element == route.focusOn) {
                        false -> Modifier
                        true -> Modifier.onGloballyPositioned { coordinates ->
                            val position = coordinates.positionInRoot()
                            focusOnElementYPosition = position.y - 300
                            focusOnElementReady = true
                        }
                    }
                ) {
                    Column(Modifier.fillMaxWidth().padding(COMMON_PADDING * 2)) {
                        ElementSignature(
                            element = element,
                            onElementClick = {
                                if (it != element || it is ProtocolDefinition) {
                                    goToDefinition(it)
                                }
                            },
                        )

                        Spacer(Modifier.height(COMMON_PADDING))

                        ElementDescription(
                            element = element,
                            onElementClick = { goToDefinition(it) },
                        )

                        Spacer(Modifier.height(COMMON_PADDING))

                        ElementAttributes(
                            element = element,
                            onElementClick = { goToDefinition(it) },
                        )
                    }
                }
            }

            Spacer(Modifier.height(OVERSCROLL_HEIGHT))
        }
    }
}
