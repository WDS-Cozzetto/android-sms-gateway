package eu.cozzetto.spidysms.modules.events

data class ExternalEvent(
    val type: ExternalEventType,
    val data: String?,
)
