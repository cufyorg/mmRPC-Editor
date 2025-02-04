package org.cufy.mmrpc.editor.util

import org.cufy.mmrpc.Comm

val COMM_CHANNEL = setOf(
    Comm.Http,
    Comm.Kafka,
)

val COMM_DIRECTION = setOf(
    Comm.Inbound,
    Comm.Outbound,
)

val COMM_SECURITY = setOf(
    Comm.SameClient,
    Comm.SameSoftware,
    Comm.SameSubject,
    Comm.SameService,
)

val COMM_STANDARD = COMM_CHANNEL + COMM_DIRECTION + COMM_SECURITY
