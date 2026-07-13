package eu.cozzetto.spidysms.modules.gateway.events

import eu.cozzetto.spidysms.modules.events.AppEvent

class WebhooksUpdatedEvent : AppEvent(NAME) {
    companion object {
        const val NAME = "WebhooksUpdatedEvent"
    }
}