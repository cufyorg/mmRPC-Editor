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
import com.charleskorn.kaml.Yaml
import io.github.vinceglb.filekit.core.FileKit
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import org.cufy.mmrpc.MmrpcSpec
import org.cufy.mmrpc.compact.strip
import org.cufy.mmrpc.compact.toCompact
import org.cufy.mmrpc.editor.COMMON_PADDING
import org.cufy.mmrpc.editor.ClientLocal

@Composable
fun ExportDropdownMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    clientLocal: ClientLocal,
    modifier: Modifier = Modifier,
) {
    val coroutineScope = rememberCoroutineScope()
    val spec by clientLocal.specState.collectAsState()

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
                .filterNot { it.isBuiltin() }
                .distinctBy { it.canonicalName }
                .map { it.toCompact() }
                .let { if (strip) it.map { it.strip() } else it }
                .toList()
        )

        val format = Yaml(
            configuration = com.charleskorn.kaml.YamlConfiguration(
                encodeDefaults = false,
                strictMode = false,
            )
        )

        FileKit.saveFile(
            bytes = format
                .encodeToString(mmrpcSpec)
                .encodeToByteArray(),
            baseName = mmrpcSpec.name,
            extension = "mmrpc.yaml",
        )
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
