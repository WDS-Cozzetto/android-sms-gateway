package eu.cozzetto.spidysms.modules.webhooks.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import eu.cozzetto.spidysms.domain.EntitySource
import eu.cozzetto.spidysms.modules.webhooks.domain.WebHookEvent

@Entity
data class WebHook(
    @PrimaryKey
    val id: String,
    val url: String,
    val event: WebHookEvent,
    val source: EntitySource,
)