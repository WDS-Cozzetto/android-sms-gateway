package eu.cozzetto.spidysms.modules.ping

import android.content.Context
import eu.cozzetto.spidysms.modules.ping.services.PingForegroundService

class PingService(
    private val settings: PingSettings,
) {
    fun start(context: Context) {
        if (!settings.enabled) return
        PingForegroundService.start(context)
    }

    fun stop(context: Context) {
        PingForegroundService.stop(context)
    }
}