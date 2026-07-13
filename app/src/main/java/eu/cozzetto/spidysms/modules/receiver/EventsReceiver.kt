package eu.cozzetto.spidysms.modules.receiver

import android.util.Log
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import eu.cozzetto.spidysms.modules.events.EventBus
import eu.cozzetto.spidysms.modules.events.EventsReceiver
import eu.cozzetto.spidysms.modules.receiver.events.MessagesExportRequestedEvent
import org.koin.core.component.get
import org.koin.core.component.inject

class EventsReceiver : EventsReceiver() {
    private val service by inject<ReceiverService>()

    override suspend fun collect(eventBus: EventBus) {
        coroutineScope {
            launch {
                Log.d("EventsReceiver", "launched MessagesExportRequestedEvent")
                eventBus.collect<MessagesExportRequestedEvent> { event ->
                    Log.d("EventsReceiver", "Event: $event")

                    service.export(
                        context = get(),
                        period = event.since to event.until,
                        messageTypes = event.messageTypes,
                        triggerWebhooks = event.triggerWebhooks,
                    )
                }
            }
        }
    }
}
