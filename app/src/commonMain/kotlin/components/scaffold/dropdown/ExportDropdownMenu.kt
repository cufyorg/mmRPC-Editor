package org.cufy.mmrpc.editor.components.scaffold.dropdown

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Block
import androidx.compose.material.icons.filled.Cable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import io.github.vinceglb.filekit.FileKit
import io.github.vinceglb.filekit.dialogs.openFileSaver
import io.github.vinceglb.filekit.writeString
import kotlinx.coroutines.launch
import org.cufy.mmrpc.MmrpcSpec
import org.cufy.mmrpc.builtin
import org.cufy.mmrpc.collect
import org.cufy.mmrpc.compact.strip
import org.cufy.mmrpc.compact.toCompact
import org.cufy.mmrpc.editor.COMMON_PADDING
import org.cufy.mmrpc.editor.Local
import org.cufy.mmrpc.experimental.toYamlString

@Composable
context(local: Local)
fun ExportDropdownMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val coroutineScope = rememberCoroutineScope()
    val spec = local.repo.displaySpec

    var stripInput by remember { mutableStateOf(false) }
    var sectionsInput by remember { mutableStateOf(spec.sections.toSet()) }

    fun onGenerateClick() = coroutineScope.launch {
        val strip = stripInput
        val sections = spec.sections.filter { it in sectionsInput }

        val mmrpcSpec = MmrpcSpec(
            name = spec.name,
            version = spec.version,
            sections = sections,
            elements = spec.elements.asSequence()
                .filter { sections.any { ns -> it.canonicalName in ns } }
                .flatMap { it.collect() }
                .filterNot { it.canonicalName in builtin }
                .distinctBy { it.canonicalName }
                .map { it.toCompact() }
                .let { if (strip) it.map { it.strip() } else it }
                .toList()
        )

        val file = FileKit.openFileSaver(
            suggestedName = mmrpcSpec.name,
            extension = "mmrpc.yaml",
        )

        file?.writeString(mmrpcSpec.toYamlString())
    }

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        content = {
            for (section in spec.sections) {
                DropdownMenuItem(
                    modifier = Modifier.defaultMinSize(minWidth = 500.dp),
                    text = { Text(section.value) },
                    onClick = {
                        if (section in sectionsInput)
                            sectionsInput -= section
                        else sectionsInput += section
                    },
                    leadingIcon = { Icon(Icons.Default.Cable, null) },
                    trailingIcon = {
                        Switch(
                            checked = section in sectionsInput,
                            onCheckedChange = {
                                if (it) sectionsInput += section
                                else sectionsInput -= section
                            },
                        )
                    }
                )
            }

            Spacer(Modifier.height(COMMON_PADDING))

            DropdownMenuItem(
                text = { Text("Strip") },
                onClick = { stripInput = !stripInput },
                leadingIcon = { Icon(Icons.Default.Block, null) },
                trailingIcon = { Checkbox(stripInput, { stripInput = it }) }
            )

            TextButton(::onGenerateClick, Modifier.fillMaxWidth(), shape = RectangleShape) {
                Text("Generate")
            }
        }
    )
}
