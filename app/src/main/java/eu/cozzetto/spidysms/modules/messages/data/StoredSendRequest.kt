package eu.cozzetto.spidysms.modules.messages.data

import eu.cozzetto.spidysms.data.entities.MessageRecipient
import eu.cozzetto.spidysms.domain.EntitySource
import eu.cozzetto.spidysms.domain.ProcessingState

class StoredSendRequest(
    val id: Long,
    val state: ProcessingState,
    val recipients: List<MessageRecipient>,
    source: EntitySource,
    message: Message,
    params: SendParams
) :
    SendRequest(source, message, params)