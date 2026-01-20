package org.cufy.mmrpc.editor.components.page.protocol

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import org.cufy.mmrpc.editor.*
import org.cufy.mmrpc.editor.common.goToDefinition
import org.cufy.mmrpc.editor.components.element.RoutineAccordion
import org.cufy.mmrpc.editor.components.element.description.ElementDescription
import org.cufy.mmrpc.editor.components.scaffold.ClientScaffold

@Composable
context(
    local: Local,
    navCtrl: MainNavController,
    route: MainRoute.Protocol,
)
fun ProtocolPage(modifier: Modifier = Modifier) {
    ClientScaffold(modifier) {
        Column(
            modifier = Modifier
                .width(COMMON_CONTENT_WIDTH)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(COMMON_PADDING),
        ) {
            SelectionContainer {
                Text(
                    text = route.element.namespace?.value ?: "[toplevel]",
                    style = MaterialTheme.typography.titleMedium,
                    fontFamily = FontFamily.Monospace,
                )
            }

            SelectionContainer {
                Text(
                    text = route.element.name,
                    style = MaterialTheme.typography.displaySmall,
                    fontFamily = FontFamily.Monospace,
                )
            }

            ElementDescription(
                element = route.element,
                onElementClick = { goToDefinition(it) },
            )

            Spacer(Modifier.height(COMMON_PADDING))

            for (routine in route.element.routines) {
                var expanded by remember { mutableStateOf(false) }

                RoutineAccordion(
                    routine = routine,
                    expanded = expanded,
                    onExpandCollapseButtonClick = { expanded = !expanded },
                    modifier = Modifier.fillMaxWidth(),
                    onElementClick = { goToDefinition(it) },
                )
            }

            Spacer(Modifier.height(OVERSCROLL_HEIGHT))
        }
    }
}
