package org.cufy.mmrpc.editor.components.scaffold

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.cufy.mmrpc.editor.Local
import org.cufy.mmrpc.editor.MainNavController
import org.cufy.mmrpc.editor.MainRoute
import org.cufy.mmrpc.editor.OVERSCROLL_HEIGHT
import org.cufy.mmrpc.editor.components.lib.CompactNavigationDrawerItem

@Composable
context(local: Local, navCtrl: MainNavController)
fun ClientNavigationDrawer(modifier: Modifier = Modifier) {
    val spec = local.repo.displaySpec

    PermanentDrawerSheet(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .then(modifier),
        drawerContainerColor = MaterialTheme.colorScheme.surface,
    ) {
        Spacer(Modifier.height(60.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            color = MaterialTheme.colorScheme.secondary,
            text = "mmRPC Editor",
        )

        Spacer(Modifier.height(40.dp))

        for (nsSection in spec.sections) {
            Text(
                text = nsSection.value,
                color = MaterialTheme.colorScheme.secondary,
                fontSize = 14.sp,
                modifier = Modifier.padding(start = 30.dp)
            )

            // sorting this way is necessary to push down longer
            // sub-namespaces (usually the namespaces of protocols)
            val nsSubSections = spec.roots.asSequence()
                .filter { it == nsSection || it != null && it in nsSection }
                .toSortedSet(compareBy({ it?.segmentsCount() }, { it }))

            Spacer(Modifier.height(5.dp))

            for (nsSubSection in nsSubSections) {
                CompactNavigationDrawerItem(
                    label =
                        nsSubSection?.value.orEmpty()
                            .removePrefix(nsSection.value)
                            .ifBlank { "[toplevel]" },
                    selected = when (val currentScreen = navCtrl.current) {
                        is MainRoute.Namespace -> currentScreen.namespace == nsSubSection
                        is MainRoute.Protocol -> currentScreen.namespace == nsSubSection

                        else -> false
                    },
                    onClick = {
                        navCtrl.push(MainRoute.Namespace(nsSubSection))
                    },
                )
            }

            Spacer(Modifier.height(15.dp))
        }

        Spacer(Modifier.height(OVERSCROLL_HEIGHT))
    }
}
