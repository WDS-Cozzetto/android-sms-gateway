package eu.cozzetto.spidysms.modules.localserver.events

import eu.cozzetto.spidysms.modules.events.AppEvent

class IPReceivedEvent(
    val localIP: String?,
    val publicIP: String?,
): AppEvent(NAME) {
    companion object {
        const val NAME = "IPReceivedEvent"
    }
}