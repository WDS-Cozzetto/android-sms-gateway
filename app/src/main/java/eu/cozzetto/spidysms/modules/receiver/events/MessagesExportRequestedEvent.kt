package eu.cozzetto.spidysms.modules.receiver.events

import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import eu.cozzetto.spidysms.extensions.configure
import eu.cozzetto.spidysms.modules.events.AppEvent
import eu.cozzetto.spidysms.modules.incoming.db.IncomingMessageType
import java.util.Date

class MessagesExportRequestedEvent(
    val since: Date,
    val until: Date,
    val messageTypes: Set<IncomingMessageType>,
    val triggerWebhooks: Boolean,
) : AppEvent(NAME) {
    data class Payload(
        @SerializedName("since")
        val since: Date,
        @SerializedName("until")
        val until: Date,
        @SerializedName("messageTypes")
        val messageTypes: String? = null,
        @SerializedName("triggerWebhooks")
        val triggerWebhooks: Boolean? = null,
    )

    companion object {
        const val NAME = "MessagesExportRequestedEvent"

        fun withPayload(payload: String): MessagesExportRequestedEvent {
            val obj = GsonBuilder().configure().create().fromJson(payload, Payload::class.java)
            val messageTypes = try {
                obj.messageTypes
                    ?.split(',')
                    ?.map { IncomingMessageType.valueOf(it) }
                    ?.toSet()
            } catch (_: Exception) {
                null
            }

            return MessagesExportRequestedEvent(
                since = obj.since,
                until = obj.until,
                messageTypes = messageTypes ?: setOf(IncomingMessageType.SMS),
                triggerWebhooks = obj.triggerWebhooks ?: true,
            )
        }
    }
}
