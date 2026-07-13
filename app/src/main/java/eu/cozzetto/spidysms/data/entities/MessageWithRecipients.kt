package eu.cozzetto.spidysms.data.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Relation

data class MessageWithRecipients(
    @Embedded val message: Message,
    @Relation(
        parentColumn = "id",
        entityColumn = "messageId",
    )
    val recipients: List<MessageRecipient>,
    @Relation(
        parentColumn = "id",
        entityColumn = "messageId",
    )
    val states: List<MessageState> = emptyList(),
    @ColumnInfo(name = "rowid")
    val rowId: Long = 0,
) {
    val state: eu.cozzetto.spidysms.domain.ProcessingState
        get() = when {
            recipients.any { it.state == eu.cozzetto.spidysms.domain.ProcessingState.Pending } -> eu.cozzetto.spidysms.domain.ProcessingState.Pending
            recipients.any { it.state == eu.cozzetto.spidysms.domain.ProcessingState.Cancelling } -> eu.cozzetto.spidysms.domain.ProcessingState.Cancelling
            recipients.any { it.state == eu.cozzetto.spidysms.domain.ProcessingState.Cancelled } -> eu.cozzetto.spidysms.domain.ProcessingState.Cancelled
            recipients.any { it.state == eu.cozzetto.spidysms.domain.ProcessingState.Processed } -> eu.cozzetto.spidysms.domain.ProcessingState.Processed

            recipients.all { it.state == eu.cozzetto.spidysms.domain.ProcessingState.Failed } -> eu.cozzetto.spidysms.domain.ProcessingState.Failed
            recipients.all { it.state == eu.cozzetto.spidysms.domain.ProcessingState.Delivered } -> eu.cozzetto.spidysms.domain.ProcessingState.Delivered
            else -> eu.cozzetto.spidysms.domain.ProcessingState.Sent
        }
}
