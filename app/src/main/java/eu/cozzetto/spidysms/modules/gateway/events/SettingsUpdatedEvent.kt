package eu.cozzetto.spidysms.modules.gateway.events

import eu.cozzetto.spidysms.modules.events.AppEvent

class SettingsUpdatedEvent : AppEvent(NAME) {

    companion object {
        const val NAME = "SettingsUpdatedEvent"
    }
}