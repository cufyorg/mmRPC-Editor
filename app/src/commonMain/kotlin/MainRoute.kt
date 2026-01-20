package org.cufy.mmrpc.editor

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.lsafer.compose.simplenav.NavController
import org.cufy.mmrpc.CanonicalName
import org.cufy.mmrpc.ElementDefinition
import org.cufy.mmrpc.ProtocolDefinition

typealias MainNavController = NavController<MainRoute>

@Serializable
sealed interface MainRoute {
    @Serializable
    @SerialName("home")
    data object Home : MainRoute

    @Serializable
    @SerialName("protocol")
    data class Protocol(
        val namespace: CanonicalName?,
        val element: ProtocolDefinition,
    ) : MainRoute

    @Serializable
    @SerialName("namespace")
    data class Namespace(
        val namespace: CanonicalName?,
        val focusOn: ElementDefinition? = null,
    ) : MainRoute
}
