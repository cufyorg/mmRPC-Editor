package org.cufy.mmrpc.editor.components.element.attributes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.cufy.mmrpc.ElementDefinition
import org.cufy.mmrpc.TraitDefinition
import org.cufy.mmrpc.editor.COMMON_PADDING
import org.cufy.mmrpc.editor.components.element.reference.FieldElementReference
import org.cufy.mmrpc.editor.util.fieldsInherited

@Composable
fun TraitElementAttributes(
    element: TraitDefinition,
    onElementClick: (ElementDefinition) -> Unit,
    modifier: Modifier = Modifier,
    highlighted: Set<String> = emptySet(),
) {
    Column(modifier) {
        for (field in element.fieldsInherited()) {
            Spacer(Modifier.height(COMMON_PADDING / 2))

            FieldElementReference(
                element = field,
                onElementClick = onElementClick,
                highlighted = field.name in highlighted,
                isOverride = true,
            )
        }

        for (field in element.fields) {
            Spacer(Modifier.height(COMMON_PADDING / 2))

            FieldElementReference(
                element = field,
                onElementClick = onElementClick,
                highlighted = field.name in highlighted,
            )
        }
    }
}
