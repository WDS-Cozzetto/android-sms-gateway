package eu.cozzetto.spidysms.modules.receiver

import eu.cozzetto.spidysms.modules.settings.KeyValueStorage
import eu.cozzetto.spidysms.modules.settings.get

class ReceiverSettings(
    private val storage: KeyValueStorage,
) {
    var contentProviderEnabled: Boolean
        get() = storage.get<Boolean>(CONTENT_PROVIDER_ENABLED) ?: true
        set(value) = storage.set(CONTENT_PROVIDER_ENABLED, value)

    companion object {
        private const val CONTENT_PROVIDER_ENABLED = "content_provider_enabled"
    }
}
