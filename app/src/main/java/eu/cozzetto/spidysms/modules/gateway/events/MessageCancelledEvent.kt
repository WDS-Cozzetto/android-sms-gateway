package eu.cozzetto.spidysms.modules.gateway.events

import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import eu.cozzetto.spidysms.extensions.configure
import eu.cozzetto.spidysms.modules.events.AppEvent

class MessageCancelledEvent(val messageId: String) : AppEvent(NAME) {
    data class Payload(
        @SerializedName("messageId")
        val messageId: String
    )

    companion object {
        fun withPayload(payload: String): MessageCancelledEvent {
            val obj = GsonBuilder().configure().create().fromJson(payload, Payload::class.java)

            return MessageCancelledEvent(
                obj.messageId,
            )
        }

        const val NAME = "MessageCancelledEvent"
    }
}
