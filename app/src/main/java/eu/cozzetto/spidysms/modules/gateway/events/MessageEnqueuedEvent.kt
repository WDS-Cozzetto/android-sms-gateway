package eu.cozzetto.spidysms.modules.gateway.events

import eu.cozzetto.spidysms.modules.events.AppEvent

class MessageEnqueuedEvent : AppEvent(NAME) {
    companion object {
        const val NAME = "MessageEnqueuedEvent"
    }
}