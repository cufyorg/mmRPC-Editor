package org.cufy.mmrpc.editor

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import co.touchlab.kermit.Logger
import org.cufy.mmrpc.editor.util.dynamicColor

/* ============= ------------------ ============= */

internal val moduleLogger = Logger.withTag("org.cufy.mmrpc.editor")

/* ============= ------------------ ============= */

const val PK_UI_SCALE = "ui.scale"
const val PK_UI_COLORS = "ui.colors"

const val PK_DATA_SPEC = "data.spec"

const val UI_SCALE_DEFAULT = 100
const val UI_SCALE_MAX = 200
const val UI_SCALE_MIN = 50
const val UI_SCALE_OFFSET = 80f
const val UI_SCALE_FACTOR = .01f
const val UI_SCALE_FONT_FACTOR = .6f

const val UI_COLORS_DEFAULT = "light"
const val UI_COLORS_DARK = "dark"
const val UI_COLORS_LIGHT = "light"

/* ============= ------------------ ============= */

val COMMON_PADDING = 10.dp
val COMMON_ELEMENT_WIDTH = 500.dp
val COMMON_CONTENT_WIDTH = 1000.dp
val TEXT_CONTENT_TOP_PADDING = 250.dp
val OVERSCROLL_HEIGHT = 50.dp

val ELEMENT_STYLE_LARGE @Composable get() = MaterialTheme.typography.titleLarge
val ELEMENT_STYLE_MEDIUM @Composable get() = MaterialTheme.typography.titleMedium
val ELEMENT_STYLE_MEDIUM_LIGHT @Composable get() = MaterialTheme.typography.bodyLarge
val ELEMENT_STYLE_SMALL: TextStyle @Composable get() = MaterialTheme.typography.titleSmall

val ELEMENT_COLOR_DEF @Composable get() = dynamicColor(Color(0xFF34495E), Color(0xFF7EB1E5))
val ELEMENT_COLOR_VALUE = Color(0xFF2ECC71)
val ELEMENT_COLOR_DESC = Color(0xFF1ABC9C)
val ELEMENT_COLOR_PROP = Color(0xFF3498DB)
val ELEMENT_COLOR_PROP_HIGHLIGHTED = Color(0xFF3445DB)
val ELEMENT_COLOR_ATTR = Color(0xFF7F8C8D)

val ELEMENT_COLOR_ENDPOINT_NAME = Color(0xFF9B59B6)
val ELEMENT_COLOR_FAULT_NAME = Color(0xFFD35400)

/* ============= ------------------ ============= */
