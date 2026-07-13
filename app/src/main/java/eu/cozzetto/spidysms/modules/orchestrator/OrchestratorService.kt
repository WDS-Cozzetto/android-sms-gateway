package eu.cozzetto.spidysms.modules.orchestrator

import android.content.Context
import eu.cozzetto.spidysms.helpers.SettingsHelper
import eu.cozzetto.spidysms.helpers.SubscriptionsHelper
import eu.cozzetto.spidysms.modules.gateway.GatewayService
import eu.cozzetto.spidysms.modules.localserver.LocalServerService
import eu.cozzetto.spidysms.modules.logs.LogsService
import eu.cozzetto.spidysms.modules.logs.db.LogEntry
import eu.cozzetto.spidysms.modules.messages.MessagesService
import eu.cozzetto.spidysms.modules.ping.PingService
import eu.cozzetto.spidysms.modules.receiver.ReceiverService
import eu.cozzetto.spidysms.modules.webhooks.WebHooksService
import eu.cozzetto.spidysms.modules.webhooks.domain.WebHookEvent
import eu.cozzetto.spidysms.modules.webhooks.payload.AppStartedPayload

class OrchestratorService(
    private val messagesSvc: MessagesService,
    private val gatewaySvc: GatewayService,
    private val localServerSvc: LocalServerService,
    private val webHooksSvc: WebHooksService,
    private val receiverService: ReceiverService,
    private val pingSvc: PingService,
    private val logsSvc: LogsService,
    private val settings: SettingsHelper,
) {
    fun start(context: Context, autostart: Boolean) {
        if (autostart && !settings.autostart) {
            return
        }

        logsSvc.start(context)
        messagesSvc.start(context)
        webHooksSvc.start(context)
        gatewaySvc.start(context)

        try {
            localServerSvc.start(context)
            pingSvc.start(context)
        } catch (e: Throwable) {
            logsSvc.insert(
                LogEntry.Priority.WARN,
                MODULE_NAME,
                "Can't start foreground services while the app is running in the background"
            )
        }

        try {
            receiverService.start(context)
        } catch (e: Throwable) {
            logsSvc.insert(
                LogEntry.Priority.WARN,
                MODULE_NAME,
                "Can't register receiver"
            )
        }

        val simCards = SubscriptionsHelper.getActiveSimCards(context)
        webHooksSvc.emit(
            context,
            WebHookEvent.AppStarted,
            AppStartedPayload(simCards)
        )
    }

    fun stop(context: Context) {
        receiverService.stop(context)
        pingSvc.stop(context)
        localServerSvc.stop(context)

        gatewaySvc.stop(context)
        webHooksSvc.stop(context)
        messagesSvc.stop(context)
        logsSvc.stop(context)
    }
}