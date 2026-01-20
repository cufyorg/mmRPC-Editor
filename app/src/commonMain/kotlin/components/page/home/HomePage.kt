package org.cufy.mmrpc.editor.components.page.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.cufy.mmrpc.ProtocolDefinition
import org.cufy.mmrpc.RoutineDefinition
import org.cufy.mmrpc.TypeDefinition
import org.cufy.mmrpc.editor.Local
import org.cufy.mmrpc.editor.MainNavController
import org.cufy.mmrpc.editor.MainRoute
import org.cufy.mmrpc.editor.components.scaffold.ClientScaffold

@Composable
context(
    local: Local,
    navCtrl: MainNavController,
    route: MainRoute.Home,
)
fun HomePage(modifier: Modifier = Modifier) {
    val spec = local.repo.displaySpec

    ClientScaffold(modifier) {
        SelectionContainer {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text("number of protocols ${spec.elements.count { it is ProtocolDefinition }}")
                Text("number of routines ${spec.elements.count { it is RoutineDefinition }}")
                Text("number of types ${spec.elements.count { it is TypeDefinition }}")
            }
        }
    }
}
