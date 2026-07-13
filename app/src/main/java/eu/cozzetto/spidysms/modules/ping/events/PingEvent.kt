package eu.cozzetto.spidysms.modules.ping.events

import eu.cozzetto.spidysms.domain.HealthResponse
import eu.cozzetto.spidysms.modules.events.AppEvent

class PingEvent(
    val health: HealthResponse,
) : AppEvent(TYPE) {
    companion object {
        const val TYPE = "PingEvent"
    }
}