package eu.cozzetto.spidysms.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    primaryKeys = ["messageId", "phoneNumber"],
    foreignKeys = [
        ForeignKey(entity = Message::class, parentColumns = ["id"], childColumns = ["messageId"], onDelete = ForeignKey.CASCADE)
    ]
)
data class MessageRecipient(
    val messageId: String,
    val phoneNumber: String,
    val state: eu.cozzetto.spidysms.domain.ProcessingState = eu.cozzetto.spidysms.domain.ProcessingState.Pending,
    val error: String? = null
)
