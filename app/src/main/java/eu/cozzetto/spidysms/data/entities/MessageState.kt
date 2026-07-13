package eu.cozzetto.spidysms.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    primaryKeys = ["messageId", "state"],
    foreignKeys = [
        ForeignKey(
            entity = Message::class,
            parentColumns = ["id"],
            childColumns = ["messageId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class MessageState(
    val messageId: String,
    val state: eu.cozzetto.spidysms.domain.ProcessingState,
    val updatedAt: Long
)
