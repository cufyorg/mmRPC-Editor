package org.cufy.mmrpc.editor

import kotlinx.serialization.Serializable
import org.cufy.mmrpc.CanonicalName
import org.cufy.mmrpc.ElementDefinition
import org.cufy.mmrpc.ProtocolDefinition

/* ============= ------------------ ============= */

@Serializable
sealed interface ClientRoute

@Serializable
data object HomePage : ClientRoute

@Serializable
data class ProtocolPage(
    val namespace: CanonicalName?,
    val element: ProtocolDefinition,
) : ClientRoute

@Serializable
data class NamespacePage(
    val namespace: CanonicalName?,
    val focusOn: ElementDefinition? = null,
) : ClientRoute

/* ============= ------------------ ============= */
