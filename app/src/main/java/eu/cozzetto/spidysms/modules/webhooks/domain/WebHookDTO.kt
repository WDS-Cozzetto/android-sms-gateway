package eu.cozzetto.spidysms.modules.webhooks.domain

import eu.cozzetto.spidysms.domain.EntitySource

data class WebHookDTO(
    val id: String?,
    val deviceId: String?,
    val url: String,
    val event: WebHookEvent,
    val source: EntitySource,
)
