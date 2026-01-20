package org.cufy.mmrpc.editor.util

import org.cufy.mmrpc.FieldDefinition
import org.cufy.mmrpc.StructDefinition
import org.cufy.mmrpc.TraitDefinition

fun StructDefinition.fieldsInherited() =
    traits.flatMap { it.fieldsInherited() + it.fields }.distinct()

fun TraitDefinition.fieldsInherited(): List<FieldDefinition> =
    traits.flatMap { it.fieldsInherited() + it.fields }.distinct()
