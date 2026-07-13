package eu.cozzetto.spidysms.modules.messages.data

import eu.cozzetto.spidysms.domain.EntitySource

open class SendRequest(
    val source: EntitySource,
    val message: Message,
    val params: SendParams,
)