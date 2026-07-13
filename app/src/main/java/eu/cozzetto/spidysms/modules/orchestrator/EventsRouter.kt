package eu.cozzetto.spidysms.modules.orchestrator

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import eu.cozzetto.spidysms.modules.events.EventBus
import eu.cozzetto.spidysms.modules.events.ExternalEvent
import eu.cozzetto.spidysms.modules.events.ExternalEventType
import eu.cozzetto.spidysms.modules.gateway.events.MessageCancelledEvent
import eu.cozzetto.spidysms.modules.gateway.events.MessageEnqueuedEvent
import eu.cozzetto.spidysms.modules.gateway.events.SettingsUpdatedEvent
import eu.cozzetto.spidysms.modules.gateway.events.WebhooksUpdatedEvent
import eu.cozzetto.spidysms.modules.receiver.events.MessagesExportRequestedEvent

class EventsRouter(
    private val eventBus: EventBus,
) {
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    fun route(event: ExternalEvent) {
        scope.launch {
            when (event.type) {
                ExternalEventType.MessageEnqueued ->
                    eventBus.emit(
                        MessageEnqueuedEvent()
                    )

                ExternalEventType.WebhooksUpdated ->
                    eventBus.emit(
                        WebhooksUpdatedEvent()
                    )

                ExternalEventType.MessagesExportRequested ->
                    eventBus.emit(
                        MessagesExportRequestedEvent.withPayload(
                            requireNotNull(event.data)
                        )
                    )

                ExternalEventType.SettingsUpdated ->
                    eventBus.emit(
                        SettingsUpdatedEvent()
                    )

                ExternalEventType.MessageCancelled -> {
                    eventBus.emit(
                        MessageCancelledEvent.withPayload(
                            requireNotNull(event.data)
                        )
                    )
                }
            }
        }
    }
}