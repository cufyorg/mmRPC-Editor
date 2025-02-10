package org.cufy.mmrpc.editor.util

import org.cufy.mmrpc.ElementDefinition

fun isInlined(element: ElementDefinition): Boolean {
    return element.name[0].isLowerCase()
}
