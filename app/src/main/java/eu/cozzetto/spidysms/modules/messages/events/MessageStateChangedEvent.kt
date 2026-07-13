package eu.cozzetto.spidysms.modules.messages.events

import eu.cozzetto.spidysms.domain.EntitySource
import eu.cozzetto.spidysms.domain.ProcessingState
import eu.cozzetto.spidysms.modules.events.AppEvent

class MessageStateChangedEvent(
    val id: String,
    val source: EntitySource,
    val phoneNumbers: Set<String>,
    val state: ProcessingState,
    val simNumber: Int?,
    val partsCount: Int?,
    val error: String?
): AppEvent(NAME) {

    companion object {
        const val NAME = "MessageStateChangedEvent"
    }
}