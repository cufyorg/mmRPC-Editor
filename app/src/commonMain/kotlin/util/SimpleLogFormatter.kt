package org.cufy.mmrpc.editor.util

import co.touchlab.kermit.Message
import co.touchlab.kermit.MessageStringFormatter
import co.touchlab.kermit.Severity
import co.touchlab.kermit.Tag
import kotlinx.datetime.TimeZone
import kotlinx.datetime.number
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock

class SimpleLogFormatter(
    private val timeZone: TimeZone,
    private val clock: Clock,
) : MessageStringFormatter {
    override fun formatMessage(severity: Severity?, tag: Tag?, message: Message) = buildString {
        val datetime = clock.now().toLocalDateTime(timeZone)

        append(datetime.year)
        append('-')
        append(datetime.month.number)
        append('-')
        append(datetime.day)
        append(' ')
        append(datetime.hour)
        append(':')
        append(datetime.minute)
        append(':')
        append(datetime.second)
        append(' ')
        if (tag != null) append("${tag.tag}: ")
        if (severity != null) append("[${severity.name.uppercase()}] - ")
        append(message.message)
    }
}
