package org.cufy.mmrpc.editor.util

import co.touchlab.kermit.Message
import co.touchlab.kermit.MessageStringFormatter
import co.touchlab.kermit.Severity
import co.touchlab.kermit.Tag
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class SimpleLogFormatter(private val clock: Clock) : MessageStringFormatter {
    override fun formatMessage(severity: Severity?, tag: Tag?, message: Message) = buildString {
        val datetime = clock.now()
            .toLocalDateTime(TimeZone.currentSystemDefault())

        append(datetime.year)
        append('-')
        append(datetime.monthNumber)
        append('-')
        append(datetime.dayOfMonth)
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
