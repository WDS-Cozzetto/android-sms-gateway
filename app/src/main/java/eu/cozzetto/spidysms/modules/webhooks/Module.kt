package eu.cozzetto.spidysms.modules.webhooks

import eu.cozzetto.spidysms.modules.webhooks.db.WebhookQueueRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val webhooksModule = module {
    singleOf(::WebHooksService)
    singleOf(::WebhookQueueRepository)
    singleOf(::WebhookPayloadStorage)
}

val NAME = "webhooks"
