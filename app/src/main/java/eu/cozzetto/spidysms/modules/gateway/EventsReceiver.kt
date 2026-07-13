package eu.cozzetto.spidysms.modules.gateway

import android.util.Log
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import eu.cozzetto.spidysms.domain.EntitySource
import eu.cozzetto.spidysms.modules.events.EventBus
import eu.cozzetto.spidysms.modules.events.EventsReceiver
import eu.cozzetto.spidysms.modules.gateway.events.DeviceRegisteredEvent
import eu.cozzetto.spidysms.modules.gateway.events.MessageCancelledEvent
import eu.cozzetto.spidysms.modules.gateway.events.MessageEnqueuedEvent
import eu.cozzetto.spidysms.modules.gateway.events.SettingsUpdatedEvent
import eu.cozzetto.spidysms.modules.gateway.events.WebhooksUpdatedEvent
import eu.cozzetto.spidysms.modules.gateway.services.SSEForegroundService
import eu.cozzetto.spidysms.modules.gateway.workers.PullMessagesWorker
import eu.cozzetto.spidysms.modules.gateway.workers.SendStateWorker
import eu.cozzetto.spidysms.modules.gateway.workers.SettingsUpdateWorker
import eu.cozzetto.spidysms.modules.gateway.workers.WebhooksUpdateWorker
import eu.cozzetto.spidysms.modules.messages.MessagesService
import eu.cozzetto.spidysms.modules.messages.events.MessageStateChangedEvent
import eu.cozzetto.spidysms.modules.ping.events.PingEvent
import org.koin.core.component.get

class EventsReceiver : EventsReceiver() {

    private val settings = get<GatewaySettings>()

    override suspend fun collect(eventBus: EventBus) {
        coroutineScope {
            launch {
                Log.d("EventsReceiver", "launched MessageEnqueuedEvent")
                eventBus.collect<MessageEnqueuedEvent> { event ->
                    Log.d("EventsReceiver", "Event: $event")

                    if (!settings.enabled) return@collect

                    PullMessagesWorker.start(get())
                }
            }
            launch {
                Log.d("EventsReceiver", "launched MessageStateChangedEvent")
                val allowedSources = setOf(EntitySource.Cloud, EntitySource.Gateway)
                eventBus.collect<MessageStateChangedEvent> { event ->
                    Log.d("EventsReceiver", "Event: $event")

                    if (!settings.enabled) return@collect

                    if (event.source !in allowedSources) return@collect

                    SendStateWorker.start(get(), event.id)
                }
            }

            launch {
                Log.d("EventsReceiver", "launched PingEvent")
                eventBus.collect<PingEvent> {
                    Log.d("EventsReceiver", "Event: $it")

                    if (!settings.enabled) return@collect

                    PullMessagesWorker.start(get())
                }
            }

            launch {
                Log.d("EventsReceiver", "launched WebhooksUpdatedEvent")
                eventBus.collect<WebhooksUpdatedEvent> {
                    Log.d("EventsReceiver", "Event: $it")

                    if (!settings.enabled) return@collect

                    WebhooksUpdateWorker.start(get())
                }
            }

            launch {
                Log.d("EventsReceiver", "launched SettingsUpdatedEvent")
                eventBus.collect<SettingsUpdatedEvent> {
                    Log.d("EventsReceiver", "Event: $it")

                    if (!settings.enabled) return@collect

                    SettingsUpdateWorker.start(get())
                }
            }

            launch {
                Log.d("EventsReceiver", "launched DeviceRegisteredEvent")
                eventBus.collect<DeviceRegisteredEvent> {
                    Log.d("EventsReceiver", "Event: $it")

                    if (!settings.enabled) return@collect
                    if (settings.fcmToken != null) return@collect

                    SSEForegroundService.start(get())
                }
            }

            launch {
                Log.d("EventsReceiver", "launched MessageCancelledEvent")
                eventBus.collect<MessageCancelledEvent> { event ->
                    Log.d("EventsReceiver", "Event: $event")

                    if (!settings.enabled) return@collect

                    try {
                        get<MessagesService>().cancelMessage(event.messageId)
                    } catch (_: IllegalArgumentException) {
                        // message not found locally — nothing to cancel
                    } catch (_: IllegalStateException) {
                        // message not in Pending state — already sent/cancelled
                    }
                }
            }
        }

    }
}