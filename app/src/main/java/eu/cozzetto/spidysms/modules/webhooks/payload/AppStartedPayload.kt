package eu.cozzetto.spidysms.modules.webhooks.payload

import eu.cozzetto.spidysms.modules.localserver.domain.SimCard

data class AppStartedPayload(
    val simCards: List<SimCard>,
)
