package eu.cozzetto.spidysms

import android.app.Application
import android.content.Context
import healthModule
import eu.cozzetto.spidysms.data.dbModule
import eu.cozzetto.spidysms.helpers.LocaleHelper
import eu.cozzetto.spidysms.modules.connection.connectionModule
import eu.cozzetto.spidysms.modules.encryption.encryptionModule
import eu.cozzetto.spidysms.modules.events.eventBusModule
import eu.cozzetto.spidysms.modules.gateway.GatewayService
import eu.cozzetto.spidysms.modules.incoming.incomingModule
import eu.cozzetto.spidysms.modules.localserver.localserverModule
import eu.cozzetto.spidysms.modules.logs.logsModule
import eu.cozzetto.spidysms.modules.messages.messagesModule
import eu.cozzetto.spidysms.modules.notifications.notificationsModule
import eu.cozzetto.spidysms.modules.orchestrator.OrchestratorService
import eu.cozzetto.spidysms.modules.orchestrator.orchestratorModule
import eu.cozzetto.spidysms.modules.ping.pingModule
import eu.cozzetto.spidysms.modules.receiver.receiverModule
import eu.cozzetto.spidysms.modules.settings.settingsModule
import eu.cozzetto.spidysms.modules.webhooks.webhooksModule
import eu.cozzetto.spidysms.receivers.EventsReceiver
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base?.let { LocaleHelper.onAttach(it) })
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                eventBusModule,
                settingsModule,
                dbModule,
                logsModule,
                notificationsModule,
                messagesModule,
                incomingModule,
                receiverModule,
                encryptionModule,
                eu.cozzetto.spidysms.modules.gateway.gatewayModule,
                healthModule,
                webhooksModule,
                localserverModule,
                pingModule,
                connectionModule,
                orchestratorModule,
            )
        }

        Thread.setDefaultUncaughtExceptionHandler(
            GlobalExceptionHandler(
                Thread.getDefaultUncaughtExceptionHandler()!!,
                get()
            )
        )

        instance = this

        EventsReceiver.register(this)

        get<OrchestratorService>().start(this, true)
    }

    val gatewayService: GatewayService by inject()

    companion object {
        lateinit var instance: App
            private set
    }
}
